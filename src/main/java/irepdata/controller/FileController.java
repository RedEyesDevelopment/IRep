package irepdata.controller;

import irepdata.model.Image;
import irepdata.service.ImageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Gvozd on 12.12.2016.
 */
@Controller
public class FileController {
    public static final String URLCLASSPREFIX = "/fileapi/";
    private final static Logger logger = Logger.getLogger(FileController.class);

    @Autowired
    private ImageService imageService;

    @RequestMapping(URLCLASSPREFIX + "fileupload")
    public String fileUploadPAge() {
        return "fileupload";
    }

    @RequestMapping(value = URLCLASSPREFIX+"uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, String publicity, HttpServletRequest request, HttpServletResponse response) {

        String name = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();

                StringBuilder rootPath = new StringBuilder(request.getServletContext().getRealPath("/").toString());
                rootPath.append("/dynamic/");
                System.out.println(rootPath.toString() );
                File dir = new File(rootPath.toString() + File.separator);

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                System.out.println("in file controller");

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                logger.info("uploaded: " + uploadedFile.getAbsolutePath());

                String fileName=uploadedFile.getName();
                Long userId = (Long) request.getSession().getAttribute("USER_ID");
                System.out.println(userId.toString());

                Image image = new Image();
                image.setImageName(fileName);
                image.setImageAuthorId(userId);
                image.setPosted(new Timestamp(System.currentTimeMillis()));
                if (!publicity.equals("")){
                    image.setPublicity(true);
                } else image.setPublicity(false);
                imageService.createImage(image);
                String redirect = "redirect:/ideas/list";
                return redirect;

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
        return "ERROR";
    }

    @RequestMapping(URLCLASSPREFIX + "nextlist")
    public String getFilesList(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        int offsetStep=1;
        for (Cookie cook:cookies){
            if (cook.getName().equals("IMAGE_OFFSET")){
                offsetStep=Integer.parseInt(cook.getValue());
            }
        }
        System.out.println("offset is: "+offsetStep);
        List<Image> imglist = imageService.getImages(offsetStep);
        Long imageCount= imageService.getImageCount();

        for (Image img: imglist) System.out.println(img.toString());

        int newCap;
        if (offsetStep<imageCount){
            newCap = offsetStep+ Image.MAXIMAGESSHOWINGCAPACITY;
        } else newCap = offsetStep;
        map.put("imageList", imglist);
        String step = Integer.toString(newCap);
        response.addCookie(new Cookie("IMAGE_OFFSET", step));
        return "fileslist";
    }

    @RequestMapping(URLCLASSPREFIX + "previouslist")
    public String getPreviousFilesList(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        int offsetStep=1;
        for (Cookie cook:cookies){
            if (cook.getName().equals("IMAGE_OFFSET")){
                offsetStep=Integer.parseInt(cook.getValue());
            }
        }
        if (offsetStep<1) offsetStep=1;
        map.put("imageList", imageService.getImages(offsetStep));
        String step;
        if (offsetStep<Image.MAXIMAGESSHOWINGCAPACITY) {
            step = "1";
        }   else step = Integer.toString(offsetStep- Image.MAXIMAGESSHOWINGCAPACITY);
        response.addCookie(new Cookie("IMAGE_OFFSET", step));
        return "fileslist";
    }
}


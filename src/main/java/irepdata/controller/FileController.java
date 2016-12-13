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
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file, String publicity, HttpServletRequest request) {// имена параметров (тут - "file") - из формы JSP.

        String name = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();

                StringBuilder rootPath = new StringBuilder(request.getServletContext().getRealPath("/").toString());
                rootPath.append("/dynamic/");
                File dir = new File(rootPath + File.separator);

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
                String string = (String) request.getAttribute("USER_ID");
                System.out.println(string);
                Long userId;
                Image image = new Image();
                image.setImageName(fileName);
//                image.setImageAuthorId(userId);
                image.setPosted(new Timestamp(System.currentTimeMillis()));
                if (!publicity.equals("")){
                    image.setPublicity(true);
                } else image.setPublicity(false);
                imageService.createImage(image);
                return "redirect:/ideas/list";

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
            if (cook.getName().equals("IMAGE_OFFSET"));
            offsetStep=Integer.parseInt(cook.getValue());
        }
        map.put("imageList", imageService.getImages(offsetStep));
        String step = Integer.toString(offsetStep+ Image.MAXIMAGESSHOWINGCAPACITY);
        response.addCookie(new Cookie("IMAGE_OFFSET", step));
        return "fileslist";
    }

    @RequestMapping(URLCLASSPREFIX + "previouslist")
    public String getPreviousFilesList(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        int offsetStep=1;
        for (Cookie cook:cookies){
            if (cook.getName().equals("IMAGE_OFFSET"));
            offsetStep=Integer.parseInt(cook.getValue());
        }
        map.put("imageList", imageService.getImages(offsetStep));
        String step;
        if (offsetStep<Image.MAXIMAGESSHOWINGCAPACITY) {
            step = "1";
        }   else step = Integer.toString(offsetStep- Image.MAXIMAGESSHOWINGCAPACITY);
        response.addCookie(new Cookie("IMAGE_OFFSET", step));
        return "fileslist";
    }
}


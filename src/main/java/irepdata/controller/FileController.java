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
        System.out.println("OFFSET: "+(Integer) request.getSession().getAttribute("IMAGE_OFFSET"));
        System.out.println("NoMoreFiles:"+request.getSession().getAttribute("NoMoreFiles"));
        System.out.println("NoLessFiles:"+request.getSession().getAttribute("NoLessFiles"));
        Integer offsetStep=0;
        Integer step = (Integer) request.getSession().getAttribute("IMAGE_OFFSET");
        if (step!=null){
            offsetStep = step;
        };
        List<Image> imglist = imageService.getImages(offsetStep);
        Long imageCount= imageService.getImageCount();

        int newCap;
        if (offsetStep<(imageCount-(Image.MAXIMAGESSHOWINGCAPACITY-1))){
            newCap = offsetStep+ Image.MAXIMAGESSHOWINGCAPACITY;
            System.out.println("NEWCAP is :" + newCap);
        } else {
            newCap = offsetStep;
            request.getSession().setAttribute("NoMoreFiles", true);
        }
        map.put("imageList", imglist);
        request.getSession().setAttribute("IMAGE_OFFSET", newCap);
        request.getSession().removeAttribute("NoLessFiles");
        return "fileslist";
    }

    @RequestMapping(URLCLASSPREFIX + "previouslist")
    public String getPreviousFilesList(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("OFFSET: "+(Integer) request.getSession().getAttribute("IMAGE_OFFSET"));
        System.out.println("NoMoreFiles:"+request.getSession().getAttribute("NoMoreFiles"));
        System.out.println("NoLessFiles:"+request.getSession().getAttribute("NoLessFiles"));
        Integer offsetStep=0;
        if (request.getSession().getAttribute("IMAGE_OFFSET")!=null){
            offsetStep = (Integer) request.getSession().getAttribute("IMAGE_OFFSET");
        } else request.getSession().setAttribute("NoLessFiles", true);
        System.out.println("offset is: "+offsetStep);
        if (offsetStep<Image.MAXIMAGESSHOWINGCAPACITY+1) {
            offsetStep=0;
            request.getSession().setAttribute("NoLessFiles", true);
        } else {
            offsetStep = offsetStep-Image.MAXIMAGESSHOWINGCAPACITY;
        }
        map.put("imageList", imageService.getImages(offsetStep));
        request.getSession().setAttribute("IMAGE_OFFSET", offsetStep);
        request.getSession().removeAttribute("NoMoreFiles");
        System.out.println(offsetStep);
        return "fileslist";
    }
}


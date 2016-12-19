package irepdata.controller;

import irepdata.model.Image;
import irepdata.service.ImageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping(value = URLCLASSPREFIX + "uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, String publicity, HttpServletRequest request, HttpServletResponse response) {

        Boolean publicitys = Boolean.parseBoolean(publicity);
        String name = null;
        if (publicitys==null) {
            publicitys = false;
        }
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();

                StringBuilder rootPath = new StringBuilder(request.getServletContext().getRealPath("/").toString());
                rootPath.append("/dynamic/");
                System.out.println(rootPath.toString());
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

                String fileName = uploadedFile.getName();
                Long userId = (Long) request.getSession().getAttribute("USER_ID");
                System.out.println(userId.toString());

                Image image = new Image();
                image.setImageName(fileName);
                image.setImageAuthorId(userId);
                image.setPosted(new Timestamp(System.currentTimeMillis()));
                if (!publicity.equals("")) {
                    image.setPublicity(publicitys);
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

    @RequestMapping(URLCLASSPREFIX + "filelistfirst")
    public String getFirstFilesList(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        Integer offsetStep = 0;
        List<Image> imglist = imageService.getImages(offsetStep);
        int newCap = offsetStep + Image.MAXIMAGESSHOWINGCAPACITY;
        map.put("imageList", imglist);
        request.getSession().setAttribute("IMAGE_OFFSET", newCap);
        request.getSession().removeAttribute("NoLessFiles");
        return "fileslistfirst";
    }


    @RequestMapping(URLCLASSPREFIX + "nextlist")
    public String getFilesList(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        Integer offsetStep = 0;
        Integer step = (Integer) request.getSession().getAttribute("IMAGE_OFFSET");
        if (step != null) {
            offsetStep = step;
        }
        System.out.println("before loading offstep is "+offsetStep);
        List<Image> imglist = imageService.getImages(offsetStep);
        Long imageCount = imageService.getImageCount();
        System.out.println("imageCount is "+ imageCount);
        int newCap;
        if (offsetStep <= (imageCount - (Image.MAXIMAGESSHOWINGCAPACITY +1))) {
            newCap = offsetStep + Image.MAXIMAGESSHOWINGCAPACITY;
            System.out.println("in first if");
        } else {
            newCap = offsetStep;
            request.getSession().setAttribute("NoMoreFiles", true);
            System.out.println("in second else");
        }
        System.out.println("newcap is "+newCap);
        map.put("imageList", imglist);
        request.getSession().removeAttribute("IMAGE_OFFSET");
        request.getSession().setAttribute("IMAGE_OFFSET", newCap);
        request.getSession().removeAttribute("NoLessFiles");
        System.out.println("offstep is " +offsetStep);
        return "fileslist";
    }

    @RequestMapping(URLCLASSPREFIX + "previouslist")
    public String getPreviousFilesList(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        Integer offsetStep = 0;
        Integer step = (Integer) request.getSession().getAttribute("IMAGE_OFFSET");
        if (step != null) {
            offsetStep = step;
            request.getSession().removeAttribute("NoLessFiles");
        } else {
            request.getSession().setAttribute("NoLessFiles", true);
        }
        if (offsetStep < Image.MAXIMAGESSHOWINGCAPACITY+1) {
            offsetStep = 0;
        } else {
            offsetStep = offsetStep - Image.MAXIMAGESSHOWINGCAPACITY;
        }
        String redirect = "fileslist";
        if (offsetStep < Image.MAXIMAGESSHOWINGCAPACITY) {
            request.getSession().setAttribute("NoLessFiles", true);
            redirect = "fileslistfirst";
        }
        map.put("imageList", imageService.getImages(offsetStep));
        request.getSession().setAttribute("IMAGE_OFFSET", offsetStep);
        request.getSession().removeAttribute("NoMoreFiles");
        System.out.println("offstep is " +offsetStep);
        return redirect;
    }
}


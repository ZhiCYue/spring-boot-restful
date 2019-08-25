package com.huami.yuezhichao.restful.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "/file")
public class FileController {


    @RequestMapping(value = "/index")
    public String index(HttpSession session, ModelMap model) {
        model.addAttribute("name", "Jay");
        model.addAttribute("age", 28);
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        MultipartFile file = request.getFile("file");
        String fileName = file.getOriginalFilename();
        if(!file.isEmpty()){
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("name:" + request.getParameter("name"));

        return "url";
    }

}

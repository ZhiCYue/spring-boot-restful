package com.huami.yuezhichao.restful.controller;

import com.huami.yuezhichao.restful.entity.User;
import com.huami.yuezhichao.restful.resp.Response;
import com.huami.yuezhichao.restful.utils.JacksonUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class IndexController {

    @RequestMapping(value = "index")
    public String index(HttpSession session, ModelMap model) {
        model.addAttribute("name", "Jay");
        model.addAttribute("age", 28);
        return "Hello.";
    }

    @RequestMapping(value = "demo", method = RequestMethod.POST)
    public ResponseEntity getList(HttpServletRequest request) {

        Response<List<User>> resp = new Response<>();
        resp.setCode("000000");
        resp.setMsg("获取了数据列表");

        List<User> users = new ArrayList<>();
        for(int i=0; i<5; i++){
            User user = new User();
            user.setEmail("test");
            users.add(user);
        }
        resp.setData(users);

        HttpHeaders headers = new HttpHeaders();
        String accept = request.getHeader("Accept");
        if("application/xml".equals(accept)){
            headers.add("Content-Type", "application/xml;charset=UTF-8");
            return new ResponseEntity<>(JacksonUtils.bean2Xml(resp), headers, HttpStatus.OK);
        }else{
            headers.add("Content-Type", "application/json;charset=UTF-8");
            return new ResponseEntity<>(resp, headers, HttpStatus.OK);
        }

    }

}

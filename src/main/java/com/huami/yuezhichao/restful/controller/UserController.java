package com.huami.yuezhichao.restful.controller;

import com.huami.yuezhichao.restful.entity.User;
import com.huami.yuezhichao.restful.resp.Response;
import com.huami.yuezhichao.restful.utils.JacksonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@Api(description = "Users 接口")
@RestController
@RequestMapping(value = "/user", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
public class UserController {

    private List<User> users = new ArrayList<>();

    public UserController() {
        for(int i=0; i<3; i++){
            User user = new User();
            user.setId(i);
            user.setUsername("Test-0" + i);
            user.setFirstname("Jay");
            user.setPhone("1832668606" + i);
            user.setLastname("Lin");
            user.setUserStatus(1);
            user.setPassword("xxx");
            user.setEmail("973294528@qq.com");
            users.add(user);
        }
    }

    @RequestMapping(value = "index")
    public String index(HttpSession session, ModelMap model) {
        model.addAttribute("name", "Jay");
        model.addAttribute("age", 28);
        return "Uses.";
    }

    /**
     * curl -X GET "http://127.0.0.1:8080/users/list" -H "accept: application/json"
     * @param request
     * @return
     */
    @ApiOperation(value = "获取用户列表" ,  notes="获取用户列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity list(HttpServletRequest request) {

        Response<List<User>> resp = new Response<>();
        resp.setCode("000000");
        resp.setMsg("获取用户列表");
        resp.setData(users);

        return resultResponse(request, resp);
    }

    /**
     * curl -X POST "http://127.0.0.1:8080/users/add" -H "accept: application/xml" -H "Content-Type: application/json" -d "{ \"username\": \"zcyue\", \"firstname\": \"yue\", \"lastname\":  \"zc\" , \"email\": \"973294528@qq.com\"}"
     * @param request
     * @param user
     * @return
     */
    @ApiOperation(value = "新增用户" ,  notes="新增用户")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ResponseEntity add(HttpServletRequest request, @Validated @RequestBody User user) {

        Response<User> resp = new Response<>();
        resp.setCode("000000");
        resp.setMsg("新增用户");
        user.setId(123);
        resp.setData(user);

        return resultResponse(request, resp);
    }

    /**
     * curl -X PUT "http://127.0.0.1:8080/users/modify" -H "accept: application/xml" -H "Content-Type: application/json" -d "{ \"id\": 1, \"username\": \"zcyue\", \"firstname\": \"yue\", \"lastname\":  \"zc\" , \"email\": \"973294528@qq.com\"}"
     * @param request
     * @param user
     * @return
     */
    @ApiOperation(value = "更新用户" ,  notes="更新用户")
    @RequestMapping(value = "modify", method = RequestMethod.PUT)
    public ResponseEntity modify(HttpServletRequest request, @Validated @RequestBody User user) {

        Response<User> resp = new Response<>();
        resp.setCode("000000");
        resp.setMsg("更新用户");
        resp.setData(user);

        return resultResponse(request, resp);
    }

    /**
     * curl -X DELETE "http://127.0.0.1:8080/users/delete/1" -H "accept: application/xml"
     * @param request
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户" ,  notes="删除用户")
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(HttpServletRequest request, @PathVariable("id") Integer id) {

        Response resp = new Response<>();
        resp.setCode("000000");
        resp.setMsg("删除用户");
        resp.setData(id);

        return resultResponse(request, resp);
    }

    private ResponseEntity resultResponse(HttpServletRequest request, Response resp) {
        HttpHeaders headers = new HttpHeaders();
        String accept = request.getHeader("Accept");

        if ("application/xml".equals(accept)){
            headers.add("Content-Type", "application/xml;charset=UTF-8");
            return new ResponseEntity<>(JacksonUtils.bean2Xml(resp), headers, HttpStatus.OK);
        } else {
            headers.add("Content-Type", "application/json;charset=UTF-8");
            return new ResponseEntity<>(resp, headers, HttpStatus.OK);
        }
    }


}

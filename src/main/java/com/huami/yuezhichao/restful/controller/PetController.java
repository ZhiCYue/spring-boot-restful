package com.huami.yuezhichao.restful.controller;

import com.alibaba.dubbo.common.utils.IOUtils;
import com.huami.yuezhichao.restful.entity.Pet;
import com.huami.yuezhichao.restful.utils.JacksonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Api(description = "用户接口")
@RestController
@RequestMapping(value = "/v2")
public class PetController {

    /**
     * curl -X POST "http://127.0.0.1:8080/v2/pet" -H "accept: application/xml" -H "Content-Type: application/json" -d "{ \"id\": 0, \"category\": { \"id\": 0, \"name\": \"string\" }, \"name\": \"doggie\", \"photoUrls\": [ \"string\" ], \"tags\": [ { \"id\": 0, \"name\": \"string\" } ], \"status\": \"available\"}"
     *
     * @param request
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "新增宠物" ,  notes="新增宠物")
    @RequestMapping(value = "/pet", method = RequestMethod.POST)
    public ResponseEntity addPet(HttpServletRequest request, @Validated @RequestBody Pet pet) throws Exception {

        // 注释掉的部分，等效于注解 @RequestBody
//        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String body = IOUtils.read(reader);
//
//        if(!StringUtils.isEmpty(body)){
//            System.out.println("receive body :"+body);
//        }
//        String contentType = request.getHeader("Content-Type");
//        Pet pet = null;
//        if("application/json".equals(contentType)){
//            pet = JacksonUtils.json2pojo(body, Pet.class);
//        } else if("application/xml".equals(contentType)){
//            pet = JacksonUtils.json2pojo(JacksonUtils.xml2json(body), Pet.class);
//        }
        System.out.println("receive: " + JacksonUtils.obj2json(pet));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/xml;charset=UTF-8");

        return new ResponseEntity<>("Invalid input", headers, HttpStatus.NOT_FOUND);
    }

    /**
     * curl -X PUT "http://127.0.0.1:8080/v2/pet" -H "accept: application/xml" -H "Content-Type: application/json" -d "{ \"category\": { \"id\": 0, \"name\": \"string\" }, \"name\": \"doggie\", \"photoUrls\": [ \"string\" ], \"tags\": [ { \"id\": 0, \"name\": \"string\" } ], \"status\": \"available\"}"
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "修改宠物" ,  notes="修改宠物")
    @RequestMapping(value = "/pet", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePet(HttpServletRequest request) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String body = IOUtils.read(reader);

        if(!StringUtils.isEmpty(body)){
            System.out.println("receive body :"+body);
        }
        String contentType = request.getHeader("Content-Type");
        Pet pet = null;
        if("application/json".equals(contentType)){
            pet = JacksonUtils.json2pojo(body, Pet.class);
        } else if("application/xml".equals(contentType)){
            pet = JacksonUtils.json2pojo(JacksonUtils.xml2json(body), Pet.class);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/xml;charset=UTF-8");

        System.out.println("receive: " + pet);
        if(pet.getId() == null) {
            return new ResponseEntity<>("Invalid ID supplied", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Invalid input", headers, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * curl -X GET "http://127.0.0.1:8080/v2/pet/findByStatus?status=available" -H "accept: application/json"
     * 或者
     * curl -X GET "http://127.0.0.1:8080/v2/pet/findByStatus?status=available" -H "accept: application/xml"
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询宠物" ,  notes="查询宠物")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "宠物标记", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/pet/findByStatus", method = RequestMethod.GET)
    public ResponseEntity<Object> findByStatus(HttpServletRequest request) throws Exception {
        String status = request.getParameter("status");
        System.out.println("receive name: " + status);

        List<Pet> pets = new ArrayList<>();
        Pet pet = new Pet();
        pet.setId(1);
        pet.setName("test_name");
        pet.setStatus(status);
        pets.add(pet);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json;charset=UTF-8");

        String accept = request.getHeader("accept");
        if("application/xml".equals(accept)) {
            return new ResponseEntity<>(JacksonUtils.bean2Xml(pets), headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(pets, headers, HttpStatus.OK);
    }

    /**
     * curl -X GET "http://127.0.0.1:8080/v2/pet/findByTags?tags=12&tags=13" -H "accept: application/xml"
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/pet/findByTags", method = RequestMethod.GET)
    public ResponseEntity<Object> findByTags(HttpServletRequest request) {
        String[] tags = request.getParameterValues("tags");

        for(int i=0; i<tags.length; i++) {
            System.out.println("receive tags: " + tags[i]);
        }

        List<Pet> pets = new ArrayList<>();
        Pet pet = new Pet();
        pet.setId(1);
        pet.setName("test_name");
        pets.add(pet);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json;charset=UTF-8");

        String accept = request.getHeader("accept");
        if("application/xml".equals(accept)) {
            return new ResponseEntity<>(JacksonUtils.bean2Xml(pets), headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(pets, headers, HttpStatus.OK);
    }

    /**
     * curl -X GET "http://127.0.0.1:8080/v2/pet/12" -H "accept: application/xml"
     *
     * @param request
     * @param petId
     * @return
     */
    @RequestMapping(value = "/pet/{petId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getById(HttpServletRequest request, @PathVariable Integer petId) {
        System.out.println("receive petId:" + petId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json;charset=UTF-8");

        String accept = request.getHeader("accept");
        if("application/xml".equals(accept)) {
            return new ResponseEntity<>("xml data", headers, HttpStatus.OK);
        }

        return new ResponseEntity<>("json data.", headers, HttpStatus.OK);
    }

    /**
     * curl -X POST "http://127.0.0.1:8080/v2/pet/1" -H "accept: application/xml" -H "Content-Type: application/x-www-form-urlencoded" -d "name=Jay&status=alive"
     *
     * @param request
     * @param petId
     * @return
     */
    @RequestMapping(value = "/pet/{petId}", method = RequestMethod.POST)
    public ResponseEntity<Object> updatePetWithForm(HttpServletRequest request, @PathVariable Integer petId) {
        System.out.println("receive petId:" + petId);

        String name = request.getParameter("name");
        System.out.println("receive name:" + name);

        String status = request.getParameter("status");
        System.out.println("receive status:" + status);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>("Invalid input", null, HttpStatus.BAD_REQUEST);
    }

}

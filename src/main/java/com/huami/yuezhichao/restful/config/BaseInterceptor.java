package com.huami.yuezhichao.restful.config;

import javax.servlet.http.HttpServletResponse;

public class BaseInterceptor {

    public void allowCors(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,PUT,OPTION,DELETE,GET");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    }

}

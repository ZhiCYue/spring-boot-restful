package com.huami.yuezhichao.restful.config;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.huami.yuezhichao.restful.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Configuration
public class RefererInterceptor extends BaseInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RefererInterceptor.class);

    @Autowired
    public Config config;


    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

        String r = request.getHeader("Referer");

        logger.info("referer:" + r);

        if (StringUtils.isEmpty(r)) {
            System.out.println("referer is empty.");
            return true;
        }

        r = r.replace("http://", "").replace("/", "");

        System.out.println(config.getReferer());
        String[] allows = config.getReferer().split(",");
        if (CommonUtils.has(Arrays.asList(allows), r)) {
            allowCors(response);
        }

        return true;
    }



}

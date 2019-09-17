package com.web.twozqj.config;

import com.web.twozqj.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyIntercptorConfig
 * Author:   落叶尘纷
 * Date:     2019/9/15 14:19
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           1.0              描述
 */
@Configuration
public class MyIntercptorConfig implements WebMvcConfigurer {
    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/user/ToReginPage.do","/user/regin.do"
                ,"/user/findAllType.do","/user/ToLoginPage.do","/user/login.do"
                        , "/user/ToIndexPage.do");
    }
}

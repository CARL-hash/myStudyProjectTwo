package com.web.twozqj.interceptor;

import com.web.twozqj.entrity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MyInterceptor
 * Author:   落叶尘纷
 * Date:     2019/9/15 14:14
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           1.0              描述
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 拦截判断
     *
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");

        System.out.println(user);

        if (user != null){
            return  true;
        }


        request.getRequestDispatcher("/user/ToIndexPage.do").forward(request,response);


        return false;
    }
}

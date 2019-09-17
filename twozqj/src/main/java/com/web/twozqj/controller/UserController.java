package com.web.twozqj.controller;

import com.web.twozqj.entrity.Type;
import com.web.twozqj.entrity.User;
import com.web.twozqj.service.UserService;
import com.web.twozqj.util.PhotoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserController
 * Author:   落叶尘纷
 * Date:     2019/9/14 20:19
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           1.0              描述
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private Integer pageSize = 3 ;

    @RequestMapping("/ToReginPage.do")
    public String toReginPage(){
        return "regin";
    }

    @RequestMapping("/regin.do")
    public String regin(String typeIds,User user){
        System.out.println(typeIds);
        userService.addUser(user,typeIds);
        return "index";
    }

    @RequestMapping("/findAllType.do")
    @ResponseBody
    public List<Type> findAllTypes(){
        List<Type> typeList = userService.findAllTypes();
        System.out.println(typeList);
        return typeList;
    }

    @RequestMapping("/ToLoginPage.do")
    public String ToLoginPage(){
        return "login";
    }


     @RequestMapping("/login.do")
     @ResponseBody
    public Boolean login(User user, HttpServletRequest request){
        System.out.println(user);
       User user1 = userService.login(user);

       if(user1 != null){
           request.getSession().setAttribute("user",user1);
           return true;
       }
       return false;
    }

    @RequestMapping("/toMenuListPage.do")
    public String toMenuListPage(){
        return  "menuList";
    }

    @RequestMapping("/findAllUsers.do")
    public String findAllUsers(Model model, @RequestParam(defaultValue = "")String name,
                               @RequestParam(defaultValue = "0") Integer pageNum){
        Page<User> page = userService.findAllUsers(name,pageNum,pageSize);

        model.addAttribute("page",page);
        model.addAttribute("name",name);

        return "userList";
    }

    @RequestMapping("/ToIndexPage.do")
    public  String toIndexPage(){
        return "index";
    }


    @RequestMapping("/ToSendPage.do")
    public  String toSendPage(HttpSession session,Model model){
        User user = (User)session.getAttribute("user");

        model.addAttribute("photo",user.getPhoto());

        return "sendPage";
    }


    @RequestMapping("/sendInfroMation.do")
    public String sendInfroMation(String user,String title,String content){
        userService.sendInfroMation(user,title,content);
        return "menuList";
    }

    @RequestMapping("/putPhotoPage.do")
    public String putPhotoPage(MultipartFile myPhoto,HttpServletRequest request) throws IOException {
        String upload = PhotoUtil.upload(myPhoto, request);
        User user = (User)request.getSession().getAttribute("user");
        user.setPhoto(upload);
       User  user1 = userService.updateUser(user);
        request.getSession().setAttribute("user",user1);
        Object user2 = request.getSession().getAttribute("user");
        User user3=(User)user2;
        System.out.println(user3.getPhoto());
        return "menuList";
    }

    @RequestMapping("/ToPutPhotoPage.do")
    public String ToPutPhotoPage(){
        return "putPhoto";
    }





}

package com.web.twozqj.service.impl;

import com.web.twozqj.dao.TypeDao;
import com.web.twozqj.dao.UserDao;
import com.web.twozqj.entrity.Type;
import com.web.twozqj.entrity.User;
import com.web.twozqj.service.UserService;
import com.web.twozqj.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserServiceImpl
 * Author:   落叶尘纷
 * Date:     2019/9/14 20:20
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           1.0              描述
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Autowired
    private JavaMailSenderImpl sender;

    private String myQQEminl ="2290616558@qq.com";
    @Override
    public void addUser(User user,String ids) {
        System.out.println(ids + user);
        String[] split = ids.split(",");
        List<Type> typeList = new ArrayList<>();
        for(String id : split){
            if(id != "" || id != null){
                Type type = new Type();
                type.setId(Integer.parseInt(id));
                typeList.add(type);
            }
        }

        user.setTypeList(typeList);

        System.out.println(user);

        userDao.save(user);
    }

    @Override
    public List<Type> findAllTypes() {
        // redis的String操作类
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        // 获取redis中的信息
        String types = valueOperations.get("types");

        List<Type> typeList = null;
        // 判断redis中是否存在
        if(types != null){
            // 存在转换成List类型
            typeList = JsonUtil.jsonToList(types, Type.class);
        }else{
            // 不存在去数据库查询
            typeList = typeDao.findAll();
        }
        return typeList;
    }

    @Scheduled(cron = "0/10 * * * * ? ")
    public void spaceTimeDoIt(){

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        List<Type> all = typeDao.findAll();

        String s = JsonUtil.listToJson(all);

        valueOperations.set("types",s);

    }

    @Override
    public User login(User user) {
        User userByNameAndPassword = userDao.findUserByNameAndPassword(user.getName(), user.getPassword());
        System.out.println(userByNameAndPassword);
        return userByNameAndPassword;
    }

    @Override
    public Page<User> findAllUsers(String name, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, new Sort(Sort.Direction.ASC, "id"));
        return userDao.getUsersByNameContaining(name, pageable);
    }

    @Override
    public void sendInfroMation(String user,String title,String content){
            SimpleMailMessage simpleMessage=new SimpleMailMessage();
            simpleMessage.setFrom(myQQEminl);
            simpleMessage.setTo(user);
            simpleMessage.setSubject(title);
            simpleMessage.setText(content);
            sender.send(simpleMessage);
    }

    @Override
    public User updateUser(User user) {
       return userDao.saveAndFlush(user);
    }


}

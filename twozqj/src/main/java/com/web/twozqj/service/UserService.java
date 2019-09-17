package com.web.twozqj.service;

import com.web.twozqj.entrity.Type;
import com.web.twozqj.entrity.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserService
 * Author:   落叶尘纷
 * Date:     2019/9/14 20:19
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           1.0              描述
 */

public interface UserService {
    void addUser(User user,String ids);

    List<Type> findAllTypes();

    User login(User user);

    Page<User> findAllUsers(String name, Integer pageNum, Integer pageSize);

    void sendInfroMation(String user,String title,String content);

    User updateUser(User user);
}

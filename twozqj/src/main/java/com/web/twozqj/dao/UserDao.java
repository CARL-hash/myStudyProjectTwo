package com.web.twozqj.dao;

import com.web.twozqj.entrity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserDao
 * Author:   落叶尘纷
 * Date:     2019/9/14 20:20
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           1.0              描述
 */
public interface UserDao extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    User findUserByNameAndPassword(String name,String password );

    Page<User> getUsersByNameContaining(String name, Pageable pageable);
}

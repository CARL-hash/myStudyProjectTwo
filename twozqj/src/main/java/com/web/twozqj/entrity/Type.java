package com.web.twozqj.entrity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Type
 * Author:   落叶尘纷
 * Date:     2019/9/14 20:23
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           1.0              描述
 */
@Entity
@Table(name="type14")
@Lazy(value = false)
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer id;
    @Column(name = "type_name")
    private String name;

    @ManyToMany(mappedBy = "typeList",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> userList;

    public Type(String name, List<User> userList) {
        this.name = name;
        this.userList = userList;
    }

    public Type() {
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}

package com.web.twozqj.entrity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: User
 * Author:   落叶尘纷
 * Date:     2019/9/14 20:21
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           1.0
 * 双向关联取消toString 的使用，或使用单项关联
 */
@Entity
@Table(name="user14")
public class User {
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_photo")
    private String photo;
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;


    @ManyToMany
    @JoinTable(name = "user14_type14",joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="type_id"))
    @JsonIgnoreProperties(value = "userList")
    private List<Type> typeList;


    public User(String name, String password, Integer id, List<Type> typeList) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.typeList = typeList;
    }

    public User() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

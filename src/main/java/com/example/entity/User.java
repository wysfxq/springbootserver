package com.example.entity;

import com.example.annotation.NotNull;

import java.io.Serializable;

/**
 * Created by yinsheng.wang on 2018/1/10.
 */
public class User implements Serializable {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

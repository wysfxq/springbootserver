package com.example.testdome;

import java.util.Date;

/**
 * Created by yinsheng.wang on 2018/4/10.
 */
public class Student {
    private String name;
    private int age;
    private Date date;

    public Student() {
        // TODO Auto-generated constructor stub
    }

    public Student(String name,int age, Date date){
        this.name = name;
        this.age = age;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

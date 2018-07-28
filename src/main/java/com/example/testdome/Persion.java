package com.example.testdome;

/**
 * Created by yinsheng.wang on 2018/5/10.
 */
public class Persion implements Cloneable {
    private Integer age;
    //private int age;//阿里规范中规定pojo类中的属性强制使用包装类型，这里只是测试

    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

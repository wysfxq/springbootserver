package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.example.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yinsheng.wang on 2018/3/24.
 */
public class TestDome extends BaseTest {
    @Autowired
    private IdGenerator idGenerator;

    @Test
    public void test1() {
        for (int i = 0; i < 100; i++) {
            System.out.println(idGenerator.generateId().longValue());
        }
    }

    @Test
    public void test2() {
        Map<String, String> map = new HashMap();
       /* map.put("studentId", 1001);
        map.put("studentName", "张三");
        map.put("age", 15);
        String student = JSON.toJSONString(map);
        System.out.println(student);*/
        String jsonStr = "{\"studentId\":1001,\"studentName\":\"张三\",\"age\":\"15\"}";
        map = (Map<String, String>) JSON.parse(jsonStr);
        User user = new User();
        user.setId((Long.valueOf(map.get("studentId").toString())));
        user.setName(map.get("studentName"));
        user.setAge(Integer.valueOf(map.get("age")));
        System.out.println(user);
    }
}

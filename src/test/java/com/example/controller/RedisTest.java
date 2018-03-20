package com.example.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yinsheng.wang on 2018/1/15.
 */
public class RedisTest extends BaseTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1() {
        redisTemplate.opsForValue().set("wys", "1986");
        Map<String, String> map = new HashMap<>();
        map.put("id", "002");
        map.put("name", "test2");
        redisTemplate.opsForValue().set("student:1002", map);
    }

    @Test
    public void test2() {
        Map<String, String> map = (Map<String, String>) redisTemplate.opsForValue().get("student");
        System.out.println(map);
    }
}

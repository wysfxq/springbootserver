package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DemoApplicationTests extends BaseTest {
    @Resource
    private UserService userService;

    @Test
    public void query() throws Exception {
        System.out.println(userService.query());
    }

    @Test
    public void addUser() throws Exception {
        Long id = Math.abs(Long.valueOf(UUID.randomUUID().hashCode()));
        User user = new User();
        user.setId(id);
        user.setName("test");
        user.setAge(19);
        userService.addUser(user);
    }

    @Test
    public void delUser() throws Exception {
        userService.delUser(null);
    }

    @Test
    public void userProce() throws Exception {
        userService.userProce();
    }

    @Test
    public void queryForMap() throws Exception {
        List<Map<String, Object>> mapList=userService.queryForMap();
        System.out.println(mapList);
    }
}

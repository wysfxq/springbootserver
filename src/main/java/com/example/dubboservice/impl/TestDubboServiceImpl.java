package com.example.dubboservice.impl;


import com.example.dubboservice.TestDubboService;
import com.example.entity.User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by yinsheng.wang on 2018/1/12.
 */
@Service
public class TestDubboServiceImpl implements TestDubboService {
    @Override
    public String getName(String name) throws Exception {
        return "姓名：" + name;
    }

    @Override
    public User getUser(Long id, String name, Integer age) throws Exception {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @Override
    public User getUserByMap(Map<String, Object> mapParams) throws Exception {
        User user = new User();
        user.setId(Long.valueOf(mapParams.get("id").toString()));
        user.setName((String) mapParams.get("name"));
        user.setAge(Integer.valueOf(mapParams.get("age").toString()));
        return user;
    }

    @Override
    public User getUserByObject(User user) throws Exception {
        User myUser = new User();
        myUser.setId(user.getId());
        myUser.setName(user.getName());
        myUser.setAge(user.getAge());
        return myUser;
    }

}

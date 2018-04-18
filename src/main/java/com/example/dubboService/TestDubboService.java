package com.example.dubboservice;

import com.example.entity.User;

import java.util.Map;

/**
 * Created by yinsheng.wang on 2018/1/11.
 */
public interface TestDubboService {
    String getName(String name) throws Exception;

    User getUser(Long id, String name, Integer age) throws Exception;

    User getUserByMap(Map<String, Object> mapParams) throws Exception;

    User getUserByObject(User user) throws Exception;
}
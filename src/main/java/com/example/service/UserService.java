package com.example.service;

import com.example.entity.User;

import java.util.List;

/**
 * Created by yinsheng.wang on 2018/1/11.
 */
public interface UserService {
    List<User> query() throws Exception;

    int addUser(User user) throws Exception;

    int delUser(Long id) throws Exception;

    void userProce() throws Exception;
}

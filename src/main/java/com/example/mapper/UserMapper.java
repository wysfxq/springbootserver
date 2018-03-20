package com.example.mapper;

import com.example.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yinsheng.wang on 2018/1/17.
 */
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setName(resultSet.getString("name"));
        user.setAge(resultSet.getInt("age"));
        user.setId(resultSet.getLong("id"));
        return user;
    }
}

package com.example.service.impl;

import com.example.annotation.Examine;
import com.example.annotation.NotNull;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yinsheng.wang on 2018/1/10.
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> query() throws Exception {
        logger.info(">>>>server层>>>>");
        String sql = "select * from user";
        List<User> userinfoList = jdbcTemplate.query(sql, new UserMapper());
        return userinfoList;
    }

    @Override
    public int addUser(User user) throws Exception {
        String sql = "insert into user(id,name,age)values('" + user.getId() + "','" + user.getName() + "','" + user.getAge() + "')";
        return jdbcTemplate.update(sql);
    }

    @Override
    @Examine(value = "fffff")
    public int delUser(@NotNull Long id) throws Exception {
        String sql = "delete from user where id='" + id + "'";
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<Map<String, Object>> queryForMap() throws Exception {
        logger.info(">>>>server层>>>>");
        String sql = "select * from user";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        return mapList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void userProce() throws Exception {
        delUser(1L);
        delUser(2L);
        throw new Exception("aaaa");
    }
}

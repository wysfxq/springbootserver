package com.example.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.example.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinsheng.wang on 2018/1/17.
 */
public class MongoDbTest extends BaseTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test1() {
        User user = new User();
        user.setId(1001L);
        user.setName("test1");
        user.setAge(12);
        mongoTemplate.save(user);
    }

    @Test
    public void test2() {
        Map<String, String> map = new HashMap();
        map.put("sid", "1002");
        map.put("sname", "aaaaaa");
        mongoTemplate.save(map, "mymap");
    }

    @Test
    public void test3() throws IOException {
        Query query = new Query();
        query.addCriteria(Criteria.where("sid").is("1001"));
        mongoTemplate.find(query, Map.class, "mymap");
        Object mapList = mongoTemplate.find(query, HashMap.class, "mymap");
        String json=JSON.json(mapList);
        System.out.println(json);
    }

    @Test
    public void test4() {
        Query query = new Query();
        query.addCriteria(Criteria.where("sid").is("1002"));
        mongoTemplate.remove(query, "mymap");
    }
}

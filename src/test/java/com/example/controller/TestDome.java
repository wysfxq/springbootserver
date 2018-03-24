package com.example.controller;

import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}

package com.example.controller;

import com.example.utils.MessageUtil;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by yinsheng.wang on 2018/1/24.
 */
public class I18nTest extends BaseTest {
    @Resource
    private MessageUtil messageUtil;

    @Test
    public void test1() {
        String value = messageUtil.getMessage("1001");
        System.out.println(value);
    }

    @Test
    public void test2() {
        String value = messageUtil.getMessage("1001", null, "", "en_US");
        System.out.println(value);
    }
}

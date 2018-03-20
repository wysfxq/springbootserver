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
        Long aa=null;
        Long bb=111L;
        if(bb.equals(aa)){
            System.out.println("111");
        }else {
            System.out.println("222");
        }

       /* String value = messageUtil.getMessage("1001");
        System.out.println(value);*/
    }
}

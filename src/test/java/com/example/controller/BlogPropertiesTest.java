package com.example.controller;

import com.example.service.impl.BlogProperties;
import com.example.service.impl.Wisely2Settings;
import com.example.utils.SpringContextUtil;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by yinsheng.wang on 2018/1/17.
 */
public class BlogPropertiesTest extends BaseTest {
    @Resource
    Wisely2Settings wisely2Settings;

    @Test
    public void test1() {
        //测试读取自定义属性值
        BlogProperties blogProperties = (BlogProperties) SpringContextUtil.getBean("blogProperties");
        System.out.println(blogProperties.getName() + "---" + blogProperties.getTitle());

       /* Wisely2Settings wisely2Settings = (Wisely2Settings) SpringContextUtil.getBean("wisely2Settings");*/
        System.out.println(wisely2Settings.getName() + "---" + wisely2Settings.getAge() + "----" + wisely2Settings.getGender());
    }
}

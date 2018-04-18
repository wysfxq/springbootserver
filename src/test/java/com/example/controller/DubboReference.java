package com.example.controller;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import com.example.entity.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinsheng.wang on 2018/2/23.
 */
public class DubboReference extends BaseTest {
    com.alibaba.dubbo.rpc.service.GenericService genericService = null;

    public void init() {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<com.alibaba.dubbo.rpc.service.GenericService>();
        reference.setInterface("com.example.dubboservice.TestDubboService");
        reference.setVersion("1.0.0.dev");
        reference.setGroup("impl");
        reference.setProtocol("dubbo");
        reference.setGeneric(true); // 声明为泛化接口
        RegistryConfig registry = new RegistryConfig();
        //从注册中心读取
        registry.setAddress("zookeeper://192.168.6.21:2181");
        //从具体主机读取(可做测试用)
        //reference.setUrl("dubbo://192.168.72.167:20899");
        reference.setRegistry(registry);
        reference.setApplication(new ApplicationConfig("wys_provider"));

        // 用com.alibaba.dubboservice.rpc.service.GenericService可以替代所有接口引用
        genericService = reference.get();


    }

    @Test
    public void test1() {
        init();
        //方法名,参数类型可以从数据库中取
        String methodName = "getName";
        String[] paramsType = new String[]{"java.lang.String"};
        Object[] paramsValue = new Object[]{"999999999"};
        // 基本类型以及Date,List,Map等不需要转换，直接调用
        Object result = genericService.$invoke(methodName, paramsType, paramsValue);
        System.out.println(result);
    }

    @Test
    public void test2() {
        init();
        //方法名,参数类型可以从数据库中取
        String methodName = "getUser";
        String[] paramsType = new String[]{"java.lang.Long", "java.lang.String", "java.lang.Integer"};
        Object[] paramsValue = new Object[]{1001330L, "wys", 30};
        // 基本类型以及Date,List,Map等不需要转换，直接调用
        Object result = genericService.$invoke(methodName, paramsType, paramsValue);
        System.out.println(result);
    }

    @Test
    public void test3() {
        init();
        //方法名,参数类型可以从数据库中取
        String methodName = "getUserByMap";
        String[] paramsType = new String[]{"java.util.Map"};
        String jsonParam = "{\n" +
                "\t\"id\": \"3333\",\n" +
                "\t\"name\": \"tttt\",\n" +
                "\t\"age\": \"20\"\n" +
                "}";
        Map<String, Object> mapParam = JSON.parseObject(jsonParam);
        Object[] paramsValue = new Object[]{mapParam};
        // 基本类型以及Date,List,Map等不需要转换，直接调用
        Object result = genericService.$invoke(methodName, paramsType, paramsValue);
        System.out.println(result);
    }

    @Test
    public void test4() throws ClassNotFoundException {
        init();
        //方法名,参数类型可以从数据库中取
        String methodName = "getUserByObject";
        String[] paramsType = new String[]{"com.example.entity.User"};
        String jsonParam = "{\n" +
                "\t\"id\": \"7777\",\n" +
                "\t\"name\": \"ddddd\",\n" +
                "\t\"age\": \"25\"\n" +
                "}";
        Map<String, Object> mapParam = JSON.parseObject(jsonParam);
        Object[] paramsValue = new Object[]{mapParam};
        // 基本类型以及Date,List,Map等不需要转换，直接调用
        Object result = genericService.$invoke(methodName, paramsType, paramsValue);
        System.out.println(result);
    }
}

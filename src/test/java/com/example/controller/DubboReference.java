package com.example.controller;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.junit.Test;

/**
 * Created by yinsheng.wang on 2018/2/23.
 */
public class DubboReference extends BaseTest {
    @Test
    public void test1() {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<com.alibaba.dubbo.rpc.service.GenericService>();

        reference.setInterface("com.example.dubboservice.TestDubboService");
        reference.setVersion("1.0.0.dev");
        reference.setGroup("impl");
        reference.setProtocol("dubbo");
        reference.setGeneric(true); // 声明为泛化接口
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://192.168.6.21:2181");
        reference.setRegistry(registry);
        reference.setApplication(new ApplicationConfig("wys_provider"));

        // 用com.alibaba.dubboservice.rpc.service.GenericService可以替代所有接口引用
        com.alibaba.dubbo.rpc.service.GenericService genericService = reference.get();
        // 基本类型以及Date,List,Map等不需要转换，直接调用

        //方法名,参数类型可以从数据库中取
        String methodName = "getName";
        String[] paramsType = new String[]{"java.lang.String"};
        Object[] paramsValue = new Object[]{"999999999"};

        Object result = genericService.$invoke(methodName, paramsType, paramsValue);
        System.out.println(result);
    }
}

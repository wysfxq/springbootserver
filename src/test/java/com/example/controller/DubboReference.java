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
        reference.setVersion("1.0.0");
        reference.setGroup("wys");
        reference.setProtocol("dubboservice");
        reference.setGeneric(true); // 声明为泛化接口
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://192.168.6.21:2181");
        reference.setRegistry(registry);
        reference.setApplication(new ApplicationConfig("call-dubboservice-demo"));

        // 用com.alibaba.dubboservice.rpc.service.GenericService可以替代所有接口引用
        com.alibaba.dubbo.rpc.service.GenericService genericService = reference.get();
        // 基本类型以及Date,List,Map等不需要转换，直接调用
        Object result = genericService.$invoke("getName", new String[] {"java.lang.String"}, new Object[] {"9999999999"});
        System.out.println(result);
    }
}

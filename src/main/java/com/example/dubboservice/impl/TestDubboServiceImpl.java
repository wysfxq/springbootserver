package com.example.dubboservice.impl;


import com.example.dubboservice.TestDubboService;
import org.springframework.stereotype.Service;

/**
 * Created by yinsheng.wang on 2018/1/12.
 */
@Service
public class TestDubboServiceImpl implements TestDubboService {
    @Override
    public String getName(String name) throws Exception {
        return "姓名：" + name;
    }
}

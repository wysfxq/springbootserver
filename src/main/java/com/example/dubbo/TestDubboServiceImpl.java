package com.example.dubbo;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * Created by yinsheng.wang on 2018/1/12.
 */
@Service(version = "1.0.0", group = "wys")
public class TestDubboServiceImpl implements TestDubboService {
    @Override
    public String getName(String name) {
        return "姓名：" + name;
    }
}

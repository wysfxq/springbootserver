package com.example.controller;

import com.example.utils.MessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by yinsheng.wang on 2018/1/11.
 */
@Controller
//@RequestMapping(value = "/order")
public class DeomController {
    @Resource
    private MessageUtil messageUtil;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/getMessage")
    @ResponseBody
    public String getMessage() {
        String value = messageUtil.getMessage("1001");
        return value;
    }
}

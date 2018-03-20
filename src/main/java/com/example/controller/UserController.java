package com.example.controller;

import com.example.DemoApplication;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.service.impl.BlogProperties;
import com.example.service.impl.Wisely2Settings;
import com.example.utils.VerificationCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.VersionedResource;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yinsheng.wang on 2018/1/11.
 */
@RestController
//@ComponentScan("com.example.service")//扫描包
//@RequestMapping(value = "user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    @Resource
    private UserService userService;
    @Resource
    private BlogProperties blogProperties;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/query")
    public List<User> query() throws Exception {
        logger.info(">>>>从数据库查询userinfo数据>>>>");
        return userService.query();
    }

    @RequestMapping(value = "/addUser")
    public void addUser(@RequestParam("name") String name, @RequestParam("age") Integer age) throws Exception {
        Long id = Math.abs(Long.valueOf(UUID.randomUUID().hashCode()));
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        userService.addUser(user);
    }

    @RequestMapping(value = "/delUser")
    public void delUser(@RequestParam("id") Long id) throws Exception {
        userService.delUser(id);
    }

    @RequestMapping(value = "/getProperties")
    public Map<String, String> getProperties() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("name", blogProperties.getName());
        map.put("title", blogProperties.getTitle());
        return map;
    }

    @RequestMapping(value = "/getServerPort")
    public String getServerPort() throws Exception {
        return serverPort;
    }

    @RequestMapping(value = "/getCode")
    public void getCode(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 将四位数字的验证码保存到Session中。
        HttpSession session = req.getSession();
        Map<String, Object> map = VerificationCodeUtil.getCode();
        System.out.println(map.get("code").toString());
        session.setAttribute("code", map.get("code").toString());
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write((BufferedImage) map.get("buffImg"), "jpeg", sos);
        sos.close();
    }
}

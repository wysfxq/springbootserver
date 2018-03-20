package com.example.controller;

import com.example.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by yinsheng.wang on 2018/1/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)// 指定spring-boot的启动类
@AutoConfigureMockMvc
@ActiveProfiles("dev")//dev环境
public class MockMvcTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void test1() throws Exception {
        String queryResult = mvc.perform(post("/query"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println("----------查询----------\n" + queryResult);

      /*  String queryResult = mvc.perform(post("/query")
               .contentType(MediaType.APPLICATION_JSON)
                .content("teste"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println("----------查询----------\n" + queryResult);*/
    }

    @Test
    public void test2() throws Exception {
        String queryResult = mvc.perform(post("/getProperties"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println("----------查询----------\n" + queryResult);
    }

    @Test
    public void test3() throws Exception {
        mvc.perform(post("/delUser").param("id", "1957190891"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
    }
}

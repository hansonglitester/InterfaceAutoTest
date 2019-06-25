package com.hsl.cn.service;

import com.hsl.cn.service.MailService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
public class TestMailService extends AbstractTestNGSpringContextTests {

    @Autowired
    private MailService mailService;


    @Test
    public void testsend(){
        mailService.sendSimpleMail("测试标题","测试内容，无需关注。。。","3129598364@qq.com","2275057986@qq.com");
    }



}

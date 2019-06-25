package com.hsl.cn.task;

import com.hsl.cn.service.MailService;
import com.hsl.cn.service.TestMailService;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.testng.TestNG;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ScheduleTask  extends TestCase {

//
//    //3.添加定时任务
//    @Scheduled(cron = "0/5 * * * * ?")
//    //或直接指定时间间隔，例如：5秒
//    //@Scheduled(fixedRate=5000)
//    private void configureTasks() {
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//    }
//
//    @Autowired
//    private MailService mailService;
//
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void sendEmail(){
//        mailService.sendSimpleMail("测试标题","测试内容，无需关注。。。","3129598364@qq.com","2275057986@qq.com");
//    }


    @Scheduled(cron = "0/5 * * * * ?")
    public void executeTest() throws InterruptedException {

        TestNG testNG = new TestNG();
        List<String> suites = new ArrayList<String>();
        suites.add("./springbootmail/src/main/resources/auto-testng.xml");
        //suites.add(".\\test-output\\testng-failed.xml");
        testNG.setTestSuites(suites);
        testNG.run();

//        // 等待执行结束，然后去执行失败用例
//        TestNG testNG1 = new TestNG();
//        List<String> suites1 = new ArrayList<String>();
//        Thread.sleep(5000);
//        suites1.add(".\\test-output\\testng-failed.xml");
//        testNG1.setTestSuites(suites1);
//        testNG1.run();


    }



}
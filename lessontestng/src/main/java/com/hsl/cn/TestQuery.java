package com.hsl.cn;

import org.testng.annotations.*;

public class TestQuery {

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("TestQuery  beforeMethod 在每个测试方法执行之前运行 ");
    }
    @Test
    public void testCase1(){
        System.out.println("TestQuery testcase01");
    }

    @Test
    public void testCase2(){
        System.out.println("TestQuery testcase02");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("TestQuery  afterMethod 在每个测试方法执行之前运行 ");
    }
}

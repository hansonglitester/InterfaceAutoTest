package com.hsl.cn;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestLogin {
    @BeforeTest
    public void BeforeTest(){
        System.out.println("TestLogin  BeforeTest ");
    }
    @Test
    public void testCase1(){
        System.out.println("TestLogin 测试case01");
    }
    @Test
    public void testCase2(){
        System.out.println("TestLogin 测试case02");
    }
    @AfterTest
    public void AfterTest(){
        System.out.println("TestLogin  AfterTest ");
    }
}
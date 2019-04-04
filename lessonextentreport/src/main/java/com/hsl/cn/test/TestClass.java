package com.hsl.cn.test;


import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClass {
    @Test
    public void test1(){
        Assert.assertEquals(1,2);
    }
    @Test
    public void test2(){
        Assert.assertEquals(1,1);
    }
    @Test
    public void test3(){
        Assert.assertEquals(2,2);
    }
    @Test
    public void test4(){
        Assert.assertEquals(1,3);
    }
}

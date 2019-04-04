package com.hsl.cn.dataprovider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProvider01 {
    @DataProvider(name="data")
    public Object[][] getdata(){
        Object[][] result=null;
        result=new Object[][]{
                {"sherry","123456"},
                {"lisa","123123"},
                {"lusy","11111"}
        };
        return  result;
    }
    @DataProvider
    public Object[][] getdata2(){
        Object[][] result=null;
        result=new Object[][]{
                {"sherry1","123456"},
                {"lisa1","123123"},
                {"lusy1","11111"}
        };
        return  result;
    }



    @DataProvider(name="methoddata")
    public Object[][] methoddata(Method method){
        Object[][] result=null;
        if("test3".equals(method.getName())){
            result=new Object[][]{
                    {"sherry","123456"},
                    {"lisa","123123"},
                    {"lusy","11111"}
            };
        }else if("test4".equals(method.getName())) {
            result=new Object[][]{
                    {"张三","123456"},
                    {"李四","123111"},
            };
        }
        return  result;
    }

    @Test(dataProvider = "data")
    public void test1(String name,String pwd){
        System.out.println("name ="+name +" pwd =" +pwd);
    }

    @Test(dataProvider="getdata2")
    public void test2(String name,String pwd){
        System.out.println("name ="+name +" pwd =" +pwd);
    }

    @Test(dataProvider="methoddata")
    public void test3(String name,String pwd){
        System.out.println("name ="+name +" pwd =" +pwd);
    }
    @Test(dataProvider="methoddata")
    public void test4(String name,String pwd){
        System.out.println("name ="+name +" pwd =" +pwd);
    }
}

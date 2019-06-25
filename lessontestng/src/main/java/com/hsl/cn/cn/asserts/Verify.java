package com.hsl.cn.cn.asserts;

import com.hsl.cn.cn.Student;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Verify {
    @Test
    public void test1(){
         //用于判断两个值是否相等
        //Assert.assertEquals(1,1);
        // Assert.assertEquals(false,false);

    }

    @Test
    public void    test2(){

        //判断是否真

        Assert.assertTrue(5>4);

    }


    @Test
    public void   test3(){
        //判断引用地址是否一致,
        Student stu =new Student(1,"hsl","1111");
        Student stu1 =new Student(1,"hsl","1111");

        System.out.println(stu.hashCode());
        System.out.println(stu1.hashCode());

        System.out.println(stu.toString());
        System.out.println(stu1.toString());
        System.out.println(stu==stu1);

        //Assert.assertEquals(stu,stu1);

        //Assert.assertSame(stu,stu1);



    }

}

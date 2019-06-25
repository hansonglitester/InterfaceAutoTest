package com.hsl.cn.cn.dataprovider;

import com.hsl.cn.cn.Student;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.*;

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
    @DataProvider
    public Object[][] getdata3(){
        Map<String ,String > map =new HashMap <>();

        map.put("id","1");
        map.put("name","2");

        Object[][] result=null;
        result=new Object[][]{{map}};
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

    @Test(dataProvider="getdata3")
    public void test5(Map<String,String> map){
        System.out.println(map.toString());
    }

    @Test
    public void test6(){

        List<String> list = new ArrayList<String>();
        list.add("张三1");
        list.add("张三2");
        list.add("张三3");
        list.add("张三4");

        //使用迭代器遍历ArrayList集合
        Iterator<String> listIt = list.iterator();
        System.out.println("eee："+listIt.toString());
        while(listIt.hasNext()){
            System.out.println(listIt.next());
        }

    }

    @Test
    public void test7(){

        Student stu1 =new Student(1,"hhhh","000001");
        Student stu2 =new Student(2,"ssss","000002");
        Student stu3 =new Student(3,"llll","000003");
        Student stu4 =new Student(4,"ssss","000004");

        List<Student> studentList =new ArrayList <>();
        studentList.add(stu1);
        studentList.add(stu2);
        studentList.add(stu3);
        studentList.add(stu4);

        List<Object[]> result =new ArrayList <>();
        Iterator<Student> studentIterator =studentList.iterator();
        while (studentIterator.hasNext()){
            result.add(new Object[]{studentIterator.next()});
        }


        Iterator<Object[]> resultit=result.iterator();
        while (resultit.hasNext()){
            Object[] ob=resultit.next();
            for (int i=0;i<ob.length;i++){
                System.out.print(ob[i]+"---");
            }


            System.out.println();
        }


    }

    @DataProvider(name="data11")
    public Iterator<Object[]>  getdata1(){
        Student stu1 =new Student(1,"hhhh","000001");
        Student stu2 =new Student(2,"ssss","000002");
        Student stu3 =new Student(3,"llll","000003");
        Student stu4 =new Student(4,"ssss","000004");

        List<Student> studentList =new ArrayList <>();
        studentList.add(stu1);
        studentList.add(stu2);
        studentList.add(stu3);
        studentList.add(stu4);

        List<Object[]> result =new ArrayList <>();
        Iterator<Student> studentIterator =studentList.iterator();
        while (studentIterator.hasNext()){
            result.add(new Object[]{studentIterator.next()});
        }


        Iterator<Object[]> resultit=result.iterator();

        return resultit;

    }

    @Test(dataProvider = "data11")
    public void test1(Student student){
        System.out.println(student.toString());
    }




    @DataProvider(name="data22")
    public Object[][] getdata22(){
        Student stu1 =new Student(5,"hhhh","000001");
        Student stu2 =new Student(6,"ssss","000002");


        Object[][] result =new Object[][]{
                {stu1},
                {stu2}
        };

        return result;

    }

    @Test(dataProvider = "data22")
    public void test22(Student student){
        System.out.println(student.toString());

    }


}

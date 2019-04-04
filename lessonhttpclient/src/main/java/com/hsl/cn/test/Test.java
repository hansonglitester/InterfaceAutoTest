package com.hsl.cn.test;

import com.hsl.cn.util.HttpClientUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException {
        Map<Object,Object> map=new HashMap<>();
        map.put("name","111");
        map.put("pwd","222");
        String result= HttpClientUtil.post("http://localhost:8090/post/Param",map);
        System.out.println(result);
    }
}

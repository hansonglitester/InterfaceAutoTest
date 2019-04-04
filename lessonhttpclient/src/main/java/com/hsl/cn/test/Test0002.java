package com.hsl.cn.test;

import com.hsl.cn.util.HttpClientUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class Test0002 {
    private CookieStore cookieStore;
    @Test
    public void test001() throws IOException {
        String result=HttpClientUtil.get("http://localhost:8080/shop/getCookie");
        System.out.println(result);
    }
    @Test
    public void test002() throws IOException {
        //发送请求的逻辑
        DefaultHttpClient httpClient=new DefaultHttpClient();
        HttpGet httpGet=new HttpGet("http://localhost:8080/shop/getCookie");
        HttpResponse httpResponse=httpClient.execute(httpGet);
        HttpEntity httpEntity= httpResponse.getEntity();
        String result= EntityUtils.toString(httpEntity,"utf-8");
        //System.out.println(result);

        /* 获取cookie */
        cookieStore = httpClient.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie:cookies) {
            System.out.println(cookie.getName()+":"+cookie.getValue());
        }
    }

}

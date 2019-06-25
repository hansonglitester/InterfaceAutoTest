package com.hsl.cn.cn.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class Test0001 {
    private String url;
    private  ResourceBundle bunble;
    private   CookieStore cookieStore;
    @BeforeClass
    public void beforeClass() {
        bunble = ResourceBundle.getBundle("config");
        url = bunble.getString("test.url");
        System.out.println(url);
    }

    @Test
    public void TestCookie() throws IOException {
        //拼接url
        String cookieUri = bunble.getString("test.cookie.uri");
        String totalUrl=url+ cookieUri;

        //发送请求的逻辑
        DefaultHttpClient httpClient=new DefaultHttpClient();
        HttpGet httpGet=new HttpGet(totalUrl);
        HttpResponse httpResponse=httpClient.execute(httpGet);
        HttpEntity httpEntity= httpResponse.getEntity();
        String result= EntityUtils.toString(httpEntity,"utf-8");
        //System.out.println(result);

        /* 获取cookie */
        cookieStore = httpClient.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
//        for (Cookie cookie:cookies) {
//            System.out.println(cookie.getName()+":"+cookie.getValue());
//        }
        System.out.println(cookies.size());
        for(int i=0;i<cookies.size();i++){
            String name=cookies.get(i).getName();
            String value=cookies.get(i).getValue();
            System.out.println(name+":"+value);

        }
    }



    @Test(dependsOnMethods = {"TestCookie"})
    public void TestGetWithCookie() throws IOException {
        //拼接url
        String getWithCookieUri= bunble.getString("test.get.with.cookie.uri");
        String totalUrl=url+getWithCookieUri;

        //发送请求的逻辑
        DefaultHttpClient httpClient=new DefaultHttpClient();
        HttpGet httpGet=new HttpGet(totalUrl);
        /*设置cookie信息*/
        httpClient.setCookieStore(cookieStore);
        HttpResponse httpResponse=httpClient.execute(httpGet);
        HttpEntity httpEntity= httpResponse.getEntity();
        String result= EntityUtils.toString(httpEntity,"utf-8");
        System.out.println(result);
    }

    @Test(dependsOnMethods = {"TestCookie"})
    public void TestGetWithCookiePost() throws IOException {
        //拼接url
        String getWithCookieUri= bunble.getString("test.post.with.cookie");
        String totalUrl=url+getWithCookieUri;

        //发送请求的逻辑
        DefaultHttpClient httpClient=new DefaultHttpClient();
        HttpPost httpPost =new HttpPost(totalUrl);
        /*设置cookie信息*/
        httpClient.setCookieStore(cookieStore);
        //设置请求头
        httpPost.setHeader("Content-type","application/json");

        //设置参数
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","huhansan");
        jsonObject.put("age","18");

        //设置参数到请求对象中,
        StringEntity entity=new StringEntity(jsonObject.toString(), "utf-8");
        String paramsStr=EntityUtils.toString(entity,"Utf-8");
        httpPost.setEntity(entity);


        //执行post/get 方法，并获得响应体
        HttpResponse httpResponse=httpClient.execute(httpPost);
        HttpEntity httpEntity= httpResponse.getEntity();
        String result= EntityUtils.toString(httpEntity,"utf-8");
        System.out.println(result);
        //处理结果和预期是否一致
        Assert.assertEquals("","");
    }

    @Test
    public void TestExpress() throws IOException {
        //拼接url
        String expressUri= bunble.getString("test.express.uri");
        String totalUrl=url+expressUri;

        //发送请求的逻辑
        HttpGet httpGet=new HttpGet(totalUrl);
        HttpClient httpClient=new DefaultHttpClient();
        HttpResponse httpResponse=httpClient.execute(httpGet);
        HttpEntity httpEntity= httpResponse.getEntity();
        String result= EntityUtils.toString(httpEntity,"utf-8");
        System.out.println(result);
    }

}

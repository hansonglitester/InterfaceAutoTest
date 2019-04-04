package com.hsl.cn.test;

import com.hsl.cn.util.HttpClientUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ResourceBundle;

public class TestExpress {
    private String url;
    private  ResourceBundle bunble;
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

        //发送请求的逻辑,获得请求的结果
        String result=HttpClientUtil.get(totalUrl);
        System.out.println(result);

        //获得响应中cookie信息

    }

    @Test
    public void TestExpress() throws IOException {
        //拼接url
        String expressUri= bunble.getString("test.express.uri");
        String totalUrl=url+expressUri;

        //发送请求的逻辑,获得请求的结果
        String result=HttpClientUtil.get(totalUrl);
        System.out.println(result);
    }

    @Test
    public void TestBaiDu() throws IOException {
        //发送请求的逻辑,获得请求的结果
        String result=HttpClientUtil.get("http://www.baidu.com/");
        System.out.println(result);
    }

}

package com.testhsl.cn.testcase;

import com.testhsl.cn.utils.HttpClientUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ResourceBundle;

public class TestUserListCase {
   public  ResourceBundle bunble = ResourceBundle.getBundle("config");
    @Test
    public void TestUserList() throws IOException {
        String url=bunble.getString("test.url")+bunble.getString("getUserInfo.uri");
        System.out.println(url);
        String result= HttpClientUtils.sendGet(url);
        System.out.println(result);

    }
}

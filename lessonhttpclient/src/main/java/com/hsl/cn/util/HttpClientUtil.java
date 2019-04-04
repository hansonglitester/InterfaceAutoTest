package com.hsl.cn.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    public static String get(String url) throws IOException {
        // 创建默认的httpClient实例
        DefaultHttpClient httpClient =new DefaultHttpClient();
        // 创建HttpGet
        HttpGet httpGet=new HttpGet(url);
        //发送请求,获得响应体
        HttpResponse httpResponse=httpClient.execute(httpGet);
        //响应实体转化为响应字符串
        HttpEntity httpEntity= httpResponse.getEntity();
        String result= EntityUtils.toString(httpEntity,"UTF-8");
        return  result;
    }

    public static String post(String url, Map<Object ,Object> params) throws IOException {
        DefaultHttpClient httpClient =new DefaultHttpClient();
        HttpPost httpPost=new HttpPost(url);
        //设置请求头

        // 设置cookies

        //添加参数，添加参数到请求队形中
        List<BasicNameValuePair> list=new ArrayList<>();
        if(params!=null){
            for(Map.Entry<Object,Object> entry:params.entrySet()){
                BasicNameValuePair bnvs=new BasicNameValuePair(entry.getKey().toString(),entry.getValue().toString());
                list.add(bnvs);
            }
        }
        UrlEncodedFormEntity urlEncodedFormEntity=new UrlEncodedFormEntity(list,"utf-8");
        httpPost.setEntity(urlEncodedFormEntity);
        //发送请求,获得响应的字符串
        HttpResponse httpResponse= httpClient.execute(httpPost);
        String result=EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        return  result;
    }
}

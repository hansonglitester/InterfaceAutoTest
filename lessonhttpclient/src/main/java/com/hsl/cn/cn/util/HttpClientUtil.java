package com.hsl.cn.cn.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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

    public static String postjson(String url,String json) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post=new HttpPost(url);

        //设置请求头
        post.setHeader("Content-type","application/json");

        //设置请求参数
        StringEntity stringEntity=new StringEntity(json,"utf-8");
        post.setEntity(stringEntity);
        //获得响应的内容
        HttpResponse response= client.execute(post);
        String result= EntityUtils.toString(response.getEntity(),"utf-8");
        return result;
    }

    //param定义  发送json数据，是json数据
    //其他数据：key=value&key=value

    public static String send(String method,Header[] headers,String url, String param) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post;
        HttpGet get;
        if(method.equals("post")){
            //创建post请求
            post=new HttpPost(url);

            //设置请求头
            if (headers!=null&&headers.length>0){
                for (Header header:headers){
                    post.setHeader(header);
                }
            }

            //设置请求参数
            StringEntity stringEntity=new StringEntity(param,"utf-8");
            post.setEntity(stringEntity);

            //获得响应的内容
            HttpResponse response= client.execute(post);
            String result= EntityUtils.toString(response.getEntity(),"utf-8");
            return result;

        }else{
            get=new HttpGet(url+param);
            if (headers!=null){
                for (Header header:headers){
                    get.setHeader(header);
                }
            }
            //获得响应的内容
            HttpResponse response= client.execute(get);
            String result= EntityUtils.toString(response.getEntity(),"utf-8");
            return result;
        }

    }
    public static String send(String method,String url, String param) throws IOException {
        return send(method,null,url,  param);
    }

    public static String send(String method,String url) throws IOException {
        return send(method,null,url,"");
    }
}

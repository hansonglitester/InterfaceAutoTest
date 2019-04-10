package com.testhsl.cn.utils;

import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class HttpClientUtils {

    public static String sendGet(String url , Map<String,Object> paramMap) {
        String reqUrl=null;
        if(url.indexOf("?") == -1){
            reqUrl=url+"?"+getReqParam(paramMap);
        }else{
            reqUrl=url+"&"+getReqParam(paramMap);
        }
        System.out.println("reqUrl:"+reqUrl);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get=new HttpGet(reqUrl);

        HttpResponse response= null;
        String result = null;
        try {
            response = client.execute(get);
            result= EntityUtils.toString(response.getEntity(),"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("url="+url+"----------reqParam="+paramMap.toString());
        }

        return  result;
    }

    public static String sendGet(String url) throws IOException {
        return sendGet(url,null);
    }

//    public static String sendPost(String url ,Map<String,Object> paramMap) throws IOException {
//
//        CloseableHttpClient client = HttpClients.createDefault();
//        String result;
//        HttpPost post=new HttpPost(url);
//
//        //设置请求参数
//        StringEntity stringEntity=new StringEntity(getReqParam(paramMap),"utf-8");
//        post.setEntity(stringEntity);
//
//        //获得响应的内容
//        HttpResponse response= client.execute(post);
//        result= EntityUtils.toString(response.getEntity(),"utf-8");
//
//        return  result;
//    }



    public static String sendPost(String url , Header[] headers, Map<String,Object> paramMap) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        String result;
        HttpPost post=new HttpPost(url);
        //设置请求头
        post.setHeaders(headers);

        //设置请求参数
        //是不是json格式的
        boolean isjosn=false;
        StringEntity stringEntity=null;
        if(headers!=null&&headers.length>0) {
            for (Header header : headers) {
                if (header.getName().contains("Content-type") && header.getValue().contains("application/json")) {
                    isjosn=true;
                    break;
                }
            }
        }

        if (isjosn){
            stringEntity=new StringEntity(getReqParamToJson(paramMap),"utf-8");
        }else {
            stringEntity=new StringEntity(getReqParam(paramMap),"utf-8");
        }

        post.setEntity(stringEntity);

        //获得响应的内容
        HttpResponse response= client.execute(post);
        result= EntityUtils.toString(response.getEntity(),"utf-8");

        return  result;
    }

    public static  String sendPost(String url ,Map<String,Object> paramMap) throws IOException {
        return  sendPost( url,null, paramMap);
    }
    public static String sendPost(String url ) throws IOException {
        return sendPost(url,null);
    }


    //将map型转为请求key=value形式
    public static String getReqParam(Map<String,Object> paramMap) {
        if (paramMap==null||paramMap.size()==0){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : paramMap.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        //最后一个参数key=value后面还有个&，去掉最后一个&
        if("&".equals(sb.substring(sb.length()-1))){
            sb.substring(0,sb.length()-1);
        }
        return sb.toString();
    }



    //将map型转为请求Gson形式
    public static String getReqParamToJson(Map<String, Object> paramMap) {
        Gson gson=new Gson();
        return gson.toJson(paramMap);
    }


}

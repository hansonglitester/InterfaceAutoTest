package com.hsl.cn.cn.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ConfigReader {
    public static ResourceBundle bunble = ResourceBundle.getBundle("config");

    public static String url=bunble.getString("test.url");


    //返回一个map,包含key,value
    public static Map<String,String > loadFile(){
        Map<String,String > map=new HashMap<String, String>();
        Enumeration<String> enumKeys=  bunble.getKeys();
        while (enumKeys.hasMoreElements()) {
            // 获取枚举中的键
            String key = (String) enumKeys.nextElement();
            // 通过 键 得到对应的 值
            String value =url+ bunble.getString(key);
            System.out.println("系统属性文件中的数据是：【" + key + "=" + value + "】");
            //放到map里面
            map.put(key,url+value);

        }

        return map;
    }

    public static String getUrl(String key){
       return loadFile().get(key);
    }

    public static void main(String[] args){
        loadFile();
    }
}

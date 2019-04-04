package com.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodService {
    public static Map<String,Object> goodList(){
        //最顶层
        Map map=new HashMap<String,Object>();
        List<Object> goodList=new ArrayList<>();
            //正常商品
            Map map1=new HashMap<String,Object>();
            map1.put("id","1");
            map1.put("goodname","测试商品1");
            map1.put("status","1");
            //删除商品
            Map map2=new HashMap<String,Object>();
            map2.put("id","2");
            map2.put("goodname","测试商品2");
            map2.put("status","2");
            //下架商品
            Map map3=new HashMap<String,Object>();
            map3.put("id","3");
            map3.put("goodname","测试商品3");
            map3.put("status","3");
            goodList.add(map1);
            goodList.add(map2);
            goodList.add(map3);

        map.put("rsp_code","0000");
        map.put("rsp_msg","成功");
        map.put("goodlist",goodList);

        return map;
    }
}

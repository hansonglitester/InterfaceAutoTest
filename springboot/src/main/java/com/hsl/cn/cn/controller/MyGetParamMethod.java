package com.hsl.cn.cn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/shop")
@Api(tags = "get方法")
public class MyGetParamMethod {
    @RequestMapping(value = "getShopList")
    @ApiOperation(value = "getShopList接口",httpMethod ="GET")
    public Map<String, Object> getShopList(@RequestParam Integer start, @RequestParam Integer offsize){
        Map<String ,Object> map=new HashMap<>();

        List<Object> list=new ArrayList<>();

        for (int i=0;i<10;i++){
            Map<String ,Object> map2=new HashMap<>();
            map2.put("衣服"+i,new Random().nextInt(100));
            list.add(map2);
        }
        map.put("shopList",list);
        map.put("msg","ok");
        return map;
    }

    @RequestMapping(value = "getShopArray")
    @ApiOperation(value = "getShopArray",httpMethod ="GET")
    public Map<String, Object> get(@RequestBody int[] a){
        for (int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
        Map<String ,Object> map=new HashMap<>();
        map.put("data",a);
        return map;
    }

    @RequestMapping(value = "getShopList/{start}/{offsize}")
    @ApiOperation(value = "getShopList2接口restful",httpMethod ="GET")
    public Map<String, Object> getShopList2(@PathVariable Integer start, @PathVariable Integer offsize){
        Map<String ,Object> map=new HashMap<>();
        String a="[{\"count\":\"1\",\"name\":\"国徽\",\"price\":\"100.00\",\"sn\":\"1553241920631\",\"unit\":\"枚\"},{\"count\":\"1\",\"name\":\"毛主席语录\",\"price\":\"200.00\",\"sn\":\"1553355131352\",\"unit\":\"枚\"},{\"count\":\"1\",\"name\":\"杂技\",\"price\":\"250.00\",\"sn\":\"1553241949655\",\"unit\":\"套\"}]";

        map.put("msg","ok");
        return map;

    }


}

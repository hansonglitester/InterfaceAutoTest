package com.hsl.cn.controller;

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

    @RequestMapping(value = "getShopList/{start}/{offsize}")
    @ApiOperation(value = "getShopList2接口restful",httpMethod ="GET")
    public Map<String, Object> getShopList2(@PathVariable Integer start, @PathVariable Integer offsize){
        Map<String ,Object> map=new HashMap<>();
        map.put("msg","ok");
        return map;
    }
}

package com.hsl.cn.cn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/shop")
public class MyGetMethod {
    @RequestMapping("getMethod")
    public String  getHello(){
        return  "helloWorld";
    }


    @RequestMapping(value ="getCookie",method = RequestMethod.GET)
    public String  getCookie(HttpServletResponse response ){
        Cookie cookie=new Cookie("login","true");
        Cookie cookie2=new Cookie("status","0000");
        response.addCookie(cookie);
        response.addCookie(cookie2);
        return  "恭喜你获得cookie信息";
    }


    @RequestMapping(value ="login",method = RequestMethod.GET)
    public String login(HttpServletRequest request ){

        Cookie[] cookies=request.getCookies();
        if(Objects.isNull(cookies)){
            return "你需要携带cookie信息";
        }
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "恭喜你登陆成功";
            }
        }
        return  "你需要携带cookie信息";
    }
}

package com.hsl.cn.cn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@Api(tags = "post方法")
public class MyPostMethod {
    //@RequestMapping(value = "post/Param",method= RequestMethod.POST)
    @PostMapping(value = "post/login")
    @ApiOperation(value ="login接口",httpMethod = "POST")
    public String  loginPost(HttpServletResponse response,@RequestParam String name, @RequestParam String pwd){
        if(name.equals("hansongli")&&pwd.equals("123456")){
            Cookie cookie=new Cookie("login","true");
            response.addCookie(cookie);
            return "登陆成功";
        }
        return "用户名或密码错误";


    }
}

package com.test.controller;

import com.test.service.GoodService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/app")
public class AppGateway {

    @RequestMapping(value = "/gateway")
    public Object gateway(HttpServletRequest request){
        if(request.getParameter("servertype").equals("goodService")){
            return GoodService.goodList();
        }
        return "方法error";

    }
}

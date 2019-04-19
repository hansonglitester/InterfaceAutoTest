package test.yizhan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import test.yizhan.dao.UserDao;
import test.yizhan.pojo.User;

@RestController
public class UserController {
    @Autowired
    UserDao userDao;

    @PostMapping(value = "/addUser")
    public String add(@RequestBody User user){
        userDao.save(user);

        return user.toString();
        user.g
    }

    @GetMapping(value = "/getUserDetail")
    public String getUserDetail(@RequestParam Integer id){
        User user=null;
        if(userDao.existsById(id)){
           user=userDao.getOne(id);

        }else{
            return "你查询的用户不存在";
        }
        return user.toString();
    }
}

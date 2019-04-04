package test.yizhan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import test.yizhan.dao.UserDao;
import test.yizhan.pojo.User;

@RestController
public class UserController {
    @Autowired
    UserDao userDao;

    public String add(){
        User user=new User();
        user.setId(1);
        user.setName("hanhan");
        userDao.save(user);
        return "ok";
    }
}

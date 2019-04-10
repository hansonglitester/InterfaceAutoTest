package test.yizhan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.yizhan.pojo.People;
import test.yizhan.pojo.User;

import java.util.List;

@RestController
public class PeopleController {
    @PostMapping(value = "/getPeople")
    public List<User> get(@RequestBody People people){
        System.out.println("getpeople");
        return people.getUserList();
    }
}

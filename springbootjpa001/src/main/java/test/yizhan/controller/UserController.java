package test.yizhan.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import test.yizhan.dao1.UserDao;
import test.yizhan.dao2.UserDao2;
import test.yizhan.pojo.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("v1")
public class UserController {
    private Logger log= LoggerFactory.getLogger(UserController.class);
    private Gson gson=new Gson();

    @Autowired
    UserDao userDao;
    @Autowired
    UserDao2 userDao2;

    //@ApiOperation(value = "添加/修改用户接口",httpMethod = "POST")
    @RequestMapping(value = "/addUser1" ,method = RequestMethod.GET)
    public String  addUser1(HttpServletRequest request){
        User user=new User();
        user.setName("hhhh");
        User user1=new User();
        user1.setName("ssss");
        userDao.save(user);
        userDao2.save(user1);

        return  "成功";
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletResponse response, @RequestBody User user){

        List<User> users=userDao.findByNameAndPwd(user.getName(),user.getPwd());
        Map<String,Object> result=new HashMap<String,Object>();
        if(users.size()==1){
            Cookie cookie = new Cookie("login","true");
            response.addCookie(cookie);

            result.put("msg","成功");
        }else {
            result.put("msg","用户名或密码不对");
        }
        return gson.toJson(result);
    }


    //@ApiOperation(value = "添加/修改用户接口",httpMethod = "POST")
    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public String  addUser(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookies(request);

        Map<String,Object> result=new HashMap<String,Object>();

        if(x!=null){
            try {
                userDao.save(user);
                result.put("msg","成功");
                result.put("data",user);
            }catch (Exception e){
                result.put("msg","数据异常,添加/修改失败");
            }
        }else{
            result.put("msg","用户未登陆");
        }
        return  gson.toJson(result);
    }



    @RequestMapping(value = "getUserDetail",method = RequestMethod.GET)
    public String getUserDetail(HttpServletRequest request, @RequestBody Integer id){

        Boolean x = verifyCookies(request);
        Map<String,Object> result=new HashMap <>();

        if(x==null){
            User user =userDao.getOne(id);
            result.put("msg","成功");
            result.put("data",user);
        }else{
            result.put("msg","用户未登陆");
        }
        return gson.toJson(result);
    }


    @RequestMapping(value = "getUserListByName",method = RequestMethod.GET)
    public String getUserListByName(HttpServletRequest request, @RequestBody User user){

        Boolean x = verifyCookies(request);
        Map<String,Object> result=new HashMap <>();

        if(x==null){
            List <User> users =new ArrayList <>();
            users=userDao.findByName(user.getName());
            result.put("msg","成功");
            result.put("data",users);
        }
        return gson.toJson(result);
    }


    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            log.info("cookies为空");
            return  false;
        }

        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login") &&
                    cookie.getValue().equals("true")){
                log.info("cookies验证通过");
                return true;
            }
        }
        return false;
    }

}
package test.yizhan;

import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import test.yizhan.pojo.People;
import test.yizhan.pojo.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {




//        People people =new People();
//        List<User> userList=new ArrayList<>();
//        User user1=new User();
//        user1.setName("zhang san");
//        userList.add(user1);
//
//        User user2=new User();
//        user2.setName("lisi");
//        userList.add(user2);
//
//        people.setUserList(userList);
//
//        Gson gson=new Gson();
//        String param=gson.toJson(people);
//
//        System.out.println(param);
       String url="http://localhost:8090/addUser";
//
        //发送请求

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post=new HttpPost(url);

        //设置请求头 发送json的数据
        post.setHeader("Content-type","application/json");

        User user =new User();
        user.setName("kkkkk");

        Gson gson=new Gson();
        String param=gson.toJson(user);

        //设置请求参数
        StringEntity stringEntity=new StringEntity(param,"utf-8");
        post.setEntity(stringEntity);
        HttpResponse response= client.execute(post);

        String result= EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);


//        //设置header信息
//        Header[] headers={new BasicHeader("Content-type", "application/json")};
//        String result= HttpClientUtil.send("post",headers,url,param);
//        System.out.println(result);


    }
}

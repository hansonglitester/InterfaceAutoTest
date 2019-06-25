package test.yizhan.dao1;

import org.springframework.data.jpa.repository.JpaRepository;
import test.yizhan.pojo.User;

import java.util.List;

public interface UserDao extends JpaRepository <User,Integer> {
    //List<User> findById(Integer id);
    List<User> findByName(String name);
    List<User> findByNameAndPwd(String name, String pwd);

}

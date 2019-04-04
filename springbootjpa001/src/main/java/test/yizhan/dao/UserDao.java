package test.yizhan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import test.yizhan.pojo.User;

public interface UserDao extends JpaRepository <User,Integer> {

}

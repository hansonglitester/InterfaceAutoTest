package test.yizhan.pojo;

import java.util.List;

public class People {
    private List<User> userList;

    public List<User> getUserList() {
        System.out.println("userlist-------");
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}

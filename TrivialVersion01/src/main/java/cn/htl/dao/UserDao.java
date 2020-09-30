package cn.htl.dao;

import cn.htl.pojo.User;

public interface UserDao {

//    User findByUserName(String username);

    void save(User user2);

    int updateStatus(String activeCode);

    User findByName(String name);
}

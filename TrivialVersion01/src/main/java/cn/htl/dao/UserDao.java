package cn.htl.dao;

import cn.htl.pojo.User;

public interface UserDao {
    User findByName(String name);

}

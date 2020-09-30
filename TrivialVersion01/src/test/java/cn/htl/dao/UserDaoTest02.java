package cn.htl.dao;

import cn.htl.pojo.User;
import cn.htl.util.MySessionUtils2;
import org.junit.Test;

import java.util.Date;

public class UserDaoTest02 {
//    @Test
//    public void test01(){
//        UserDao dao = MySessionUtils2.getSession().getMapper(UserDao.class);
//        User user = dao.findByUserName("htl123456");
//        System.out.println(user);
//
//    }
    @Test
    public void test02(){
        UserDao dao = MySessionUtils2.getSession().getMapper(UserDao.class);
        User user = new User(10,"hutailang10086","hutailang10086",new Date(),"虎太郎","男","17742661629","848112204@qq.com","N","ddd");
        dao.save(user);
        MySessionUtils2.commitAndClose();
    }
    @Test
    public void test03(){
        UserDao dao = MySessionUtils2.getSession().getMapper(UserDao.class);
    }
}

package cn.htl.service;

import cn.htl.pojo.User;
import org.junit.Test;

import java.util.Date;

public class TestUserService {
    @Test
    public void test01(){
        UserService userService = new UserService();
        User user = userService.findUserByName("jackhell");
        if (user != null){
            System.out.println("存在");
        }else {
            System.out.println("不存在");
        }
    }
    @Test
    public void test02(){
        UserService userService = new UserService();
        User user = new User();
        user.setUsername("jackhello");
        user.setPassword("jackhello");
        user.setStatus("Y");
        int code = userService.login(user);
        System.out.println(code);
    }
//    @Test
//    public void test03(){
//        UserService userService = new UserService();
//        User user = new User(2, "htl123456", "htl123456", new Date(), "虎太郎", "男", "2222", "22222@qq.com", "N", "dddd");
//        int code = userService.register(user);
//
//        System.out.println(code);
//    }



}

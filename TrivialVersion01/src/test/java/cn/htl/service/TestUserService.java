package cn.htl.service;

import cn.htl.pojo.User;
import org.junit.Test;

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
        user.setStatus('Y');
        int code = userService.login(user);
        System.out.println(code);
    }

}

package cn.htl.service;



import cn.htl.dao.UserDao;
import cn.htl.pojo.User;
import cn.htl.util.MailUtils;
import cn.htl.util.MySessionUtils2;
import cn.htl.util.UuidUtil;



public class UserService {
    //直接获取Dao

    public User findUserByName(String name) {
        //getMapper是mybatis,给接口生成实现类，将实现类对象返回
        UserDao userDao = MySessionUtils2.getMapper(UserDao.class);
        //根据用户名查找用户
        User user = userDao.findByName(name);
        MySessionUtils2.commitAndClose();
        return user;
    }

    public int login(User user) {
        //账号密码
        UserDao userDao = MySessionUtils2.getMapper(UserDao.class);
        User u = userDao.findByName(user.getUsername());
        //比对数据库的账号密码
        if (u == null) {
            return -1;//找不到jack
        } else {
            //判断是否是激活的账户
            if ("Y".equals(u.getStatus())) {
                //是激活用户
                if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                    return 1;//提示登录成功
                } else {
                    return -2;//账号或者密码出错
                }
            } else {
                return -3;//已激活
            }
        }
    }

//    public int register(User user) {
//        UserDao userDao = MySessionUtils2.getMapper(UserDao.class);
//        //判断 用户的账号是否存在
//        User user2 = userDao.findByUserName(user.getUsername());
//        //不存在，调用保存 返回 1
//        if(user2 == null){
//            user.setStatus("N");//未激活
//            String activeCode = UuidUtil.getUuid();
//            user.setCode(activeCode);//激活
//            userDao.save(user);
//            MySessionUtils2.commitAndClose();
//
//            //参1 收件人
//            //参2 内容
//            //参3 标题
//            MailUtils.sendMail(user.getEmail(),"<a href='http://localhost:8080/lvyou_war_exploded/activeServlet?activeCode="+activeCode+"'>点击激活途牛旅游账户</a>","激活账户");
//            return 1;
//        }else{
//            //存在，返回-1
//            return -1;
//        }
//
//    }
//
//    public int active(String activeCode) {
//        UserDao userDao = MySessionUtils2.getMapper(UserDao.class);
//        int code =  userDao.updateStatus(activeCode); //1 表示成功
//        MySessionUtils2.commitAndClose();
//        return code;
//    }
}

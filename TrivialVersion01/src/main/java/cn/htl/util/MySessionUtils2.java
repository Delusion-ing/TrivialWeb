package cn.htl.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

//Session工具类
public class MySessionUtils2 {
    private static SqlSessionFactory sessionFactory;
    //static 静态代码，在类加载的时候执行一次，且只执行一次
    static{
//  》1 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
// 》2 创建SqlSessionFactory对象
        InputStream inputStream = MySessionUtils2.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
         sessionFactory = sqlSessionFactoryBuilder.build(inputStream);//加载核心配置文件 参1 输入流
//        》3 加载SqlMapConfig.xml配置文件
    }

    //A: 定义一个ThreadLocal集合，本质是Map<Thread,Object> map
    private   static ThreadLocal<SqlSession> map = new ThreadLocal<SqlSession>();

    public static SqlSession getSession() {

        //查找在local中，是否有对应的SqlSession
        SqlSession sqlSession = map.get(); //map.get(Thread.currentThread())

        if (sqlSession != null) {
            //有就直接返回给调用者使用
            return sqlSession;
        } else {

            //没有就创建一个新的，并且保存在local
            sqlSession = sessionFactory.openSession();
            //保存
            map.set(sqlSession);

            return sqlSession;
        }
    }

    public static void commitAndClose() {
        //将来进行写操作，之后需要提交，我们定义的方法
        SqlSession session = map.get();
        if (session != null) {
            session.commit();//提交
            session.close();//释放
            //已经关闭的session不能留在local
            //所以要删除
            map.remove();
        }
    }

    public static void rollbackAndClose() {
        //将来进行写操作，之后需要提交，我们定义的方法
        SqlSession session = map.get();
        if (session != null) {
            session.rollback();//回滚
            session.close();//释放
            //已经关闭的session不能留在local
            //所以要删除
            map.remove();
        }
    }
}

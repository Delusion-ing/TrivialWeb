package cn.htl.util;

import org.junit.Test;

public class MailUtilTest {
    @Test
    public void sendMail(){

        MailUtils.sendMail("htl848112204@163.com","<a href='http://localhost:8080/lvyou_war_exploded/activeServlet?activeCode=bb26f0d05ea745c5986bc18ff7b4cef9'>点击激活途牛旅游账户</a>","激活账户");
    }
}

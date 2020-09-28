package cn.htl.web.servlet;

import cn.htl.pojo.ResponseInfo;
import cn.htl.pojo.User;
import cn.htl.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,String[]> map = req.getParameterMap();
        User u =new User();
        try {
            BeanUtils.populate(u,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService userService = new UserService();
        int code = userService.login(u);
        ResponseInfo info = new ResponseInfo();
        info.setCode(code);
        if (code ==-1){
            info.setData("用户不存在");

        }else if (code == -2){
            info.setData("登录成功");
        }
        else if (code == -3){
            info.setData("账号未激活请激活");
        }
        String json = new ObjectMapper().writeValueAsString(info);
        resp.getWriter().println(json);
    }
}

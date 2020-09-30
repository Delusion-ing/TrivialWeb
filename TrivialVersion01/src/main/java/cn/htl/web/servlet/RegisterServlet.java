package cn.htl.web.servlet;

import cn.htl.pojo.ResponseInfo;
import cn.htl.pojo.User;
import cn.htl.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    //将String类型转成Date类型的注册器交给beanUtils
    static {
        ConvertUtils.register(new Converter() {

            public Object convert(Class type, Object value) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return simpleDateFormat.parse(value.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, Date.class);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check1 = request.getParameter("check");
        String check2 = (String)request.getSession().getAttribute("CHECKCODE_SERVER");
        System.out.println(check1);
        System.out.println(check2);
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        //比较check1与check2是否相同

        if(check1 == null ||!check1.equalsIgnoreCase(check2)){
            ResponseInfo responseInfo = new ResponseInfo();
            responseInfo.setCode(-4);
            responseInfo.setData("注册失败，验证码出错");

            //数据格式转成json
            String json = new ObjectMapper().writeValueAsString(responseInfo);
            response.getWriter().println(json);
            return;
        }


        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        //请求的参数赋值给变量
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("注册数据："+user);
        UserService userService = new UserService();
        int code = userService.register(user);

        ResponseInfo responseInfo = new  ResponseInfo();
        responseInfo.setCode(code);
        if (code == 1){
            responseInfo.setData("注册成功");
        }else {
            responseInfo.setData("注册失败");
        }
        String json = new ObjectMapper().writeValueAsString(responseInfo);
        response.getWriter().println(json);
    }
}

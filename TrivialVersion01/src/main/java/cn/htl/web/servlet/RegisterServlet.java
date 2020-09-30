package cn.htl.web.servlet;

import cn.htl.pojo.ResponseInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
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
    }
}

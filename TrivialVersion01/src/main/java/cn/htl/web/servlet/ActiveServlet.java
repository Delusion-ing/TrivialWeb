package cn.htl.web.servlet;

import cn.htl.pojo.User;
import cn.htl.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeServlet")
public class ActiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activeCode = request.getParameter("activeCode");
        UserService userService = new UserService();
        int code = userService.active(activeCode);
      System.out.println(activeCode);

        String msg = "";
     if (code == 1){
            msg="激活成功，可以使用";
       }else {
            msg="激活失败";
       }
       request.setAttribute("msg",msg);
       request.getRequestDispatcher("message.jsp").forward(request,response);
   }
}

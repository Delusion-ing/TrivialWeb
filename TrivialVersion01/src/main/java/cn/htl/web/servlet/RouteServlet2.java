package cn.htl.web.servlet;

import cn.htl.pojo.PageBean;
import cn.htl.pojo.ResponseInfo;
import cn.htl.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/routeServlet2")
public class RouteServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  cidStr = request.getParameter("cid");
        String  currentPageStr = request.getParameter("currentPage");
        String  pageSizeStr = request.getParameter("pageSize");

        int cid=0;
        cid=Integer.parseInt(cidStr);

        int currentPage = 1;
        currentPage=Integer.parseInt(currentPageStr);

        int pageSize = 20;
        pageSize = Integer.parseInt(pageSizeStr);

        //属于业务的相关处理
        RouteService routeService = new RouteService();
        PageBean pb = routeService.findPageByCid(cid,currentPage,pageSize);

        ResponseInfo responseInfo =new ResponseInfo();
        responseInfo.setCode(200);
        responseInfo.setData(pb);
        String json = new ObjectMapper().writeValueAsString(responseInfo);
        response.getWriter().println(json);
    }
}

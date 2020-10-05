package cn.htl.web.servlet;

import cn.htl.pojo.Category;
import cn.htl.pojo.ResponseInfo;
import cn.htl.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/categoryServlet")
public class CategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建业务对象
        CategoryService categoryService = new CategoryService();
        List<Category> categoryList = categoryService.findAllCategory();

        //显示出来
        ResponseInfo info = new ResponseInfo();
        info.setCode(200);
        info.setData(categoryList);
        String json = new ObjectMapper().writeValueAsString(info);
        response.getWriter().println(json);
    }
}

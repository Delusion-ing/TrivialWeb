package cn.htl.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决全站乱码问题，处理所有的请求
 */
@WebFilter("/*")
public class CharchaterFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {

       req.setCharacterEncoding("utf-8");
        // 处理响应乱码
        rep.setContentType("text/html;charset=utf-8");
        // 放行请求
        filterChain.doFilter(req,rep);
    }

    public void destroy() {

    }
}

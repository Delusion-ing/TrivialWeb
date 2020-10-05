package cn.htl.service;

import cn.htl.pojo.PageBean;
import org.junit.Test;

public class TestRouteService {
    @Test
    public void Test01(){
        RouteService routeService = new RouteService();

        PageBean pageBean = routeService.search("长沙",1,20);
        System.out.println(pageBean);


    }
}

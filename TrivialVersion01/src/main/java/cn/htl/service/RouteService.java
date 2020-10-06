package cn.htl.service;


import cn.htl.dao.RouteDao;
import cn.htl.pojo.PageBean;
import cn.htl.pojo.Route;
import cn.htl.util.MySessionUtils2;

import java.util.List;

public class RouteService {
    //搜索功能
    public PageBean search(String keyword, int currentPage, int pageSize) {
        //创建分页数据对象  包含4个整数与一个集合
        PageBean pb = new PageBean();
        //设置当前页号
        pb.setCurrentPage(currentPage);//1*
        //设置每页记录数
        pb.setPageSize(pageSize);//2
        //设置总记录数
        RouteDao routeDao = MySessionUtils2.getMapper(RouteDao.class);
        //'%长沙%'
        int totalCount = routeDao.findCountPageName("%" + keyword + "%");
        pb.setTotalCount(totalCount);//3*

        //设置总页数  每页最多只能放20条   40  2  与 41  3
        //在java中，整数相除只保留整数部，丢失小数部  41/20  就是2
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        pb.setTotalPage(totalPage);//4*

        //设置PageBean中的list集合
        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findPageByName("%" + keyword + "%", start, pageSize);
        pb.setList(list);
        return pb;
    }

    public PageBean findPageByCid(int cid, int currentPage, int pageSize) {
        PageBean pb = new PageBean();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        RouteDao routeDao = MySessionUtils2.getMapper(RouteDao.class);

        int totalCount = routeDao.findCountById(cid);
        pb.setTotalCount(totalCount);

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        pb.setTotalPage(totalPage);

        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findPageById(cid, start, pageSize);
        pb.setList(list);
        return pb;


    }
}

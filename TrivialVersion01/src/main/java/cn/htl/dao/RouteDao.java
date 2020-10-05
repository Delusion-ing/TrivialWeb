package cn.htl.dao;

import cn.htl.pojo.Route;

import java.util.List;

public interface RouteDao {
    int findCountPageName(String keyword);

    List<Route> findPageByName(String keyword, int start, int pageSize);
}

package cn.htl.service;

import cn.htl.pojo.PageBean;
import cn.htl.pojo.ResponseInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.transform.Rotate;
import org.junit.Test;

public class TestRouteService {
    @Test
    public void Test01(){
        RouteService routeService = new RouteService();

        PageBean pageBean = routeService.search("长沙",1,20);
        System.out.println(pageBean);


    }
    @Test
    public void Test02()throws JsonProcessingException{

        RouteService routeService = new RouteService();
        //根据指定的分类来查找 但是路线需要使用分页显示
        PageBean pageBean = routeService.findPageByCid(1,2,20);
        //json生成
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(200);
        responseInfo.setData(pageBean);

        String json = new ObjectMapper().writeValueAsString(responseInfo);

        System.out.println(json);
    }
}

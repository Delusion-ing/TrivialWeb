package cn.htl.service;

import cn.htl.dao.CategoryDao;
import cn.htl.pojo.Category;
import cn.htl.util.MySessionUtils2;

import java.util.List;

public class CategoryService {
    public List<Category> findAllCategory() {
        CategoryDao dao = MySessionUtils2.getMapper(CategoryDao.class);
        List<Category>list = dao.findAllCategory();
        return list;
    }
}

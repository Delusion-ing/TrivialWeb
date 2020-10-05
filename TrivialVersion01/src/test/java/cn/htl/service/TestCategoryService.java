package cn.htl.service;

import cn.htl.pojo.Category;
import org.junit.Test;

import java.util.List;

public class TestCategoryService {
    @Test
    public void Test01(){
        CategoryService categoryService = new CategoryService();
        List<Category> categoryList = categoryService.findAllCategory();
        System.out.println(categoryList);
    }
}

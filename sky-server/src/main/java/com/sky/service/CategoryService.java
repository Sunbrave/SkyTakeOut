package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;
import org.springframework.stereotype.Service;

/**
 * @description: 菜品分类接口
 */

public interface CategoryService {

    void saveCategory(CategoryDTO categoryDTO);

    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO );

    void deleteCategory(Long id);

    void update(CategoryDTO categoryDTO);

    void startOrStop(Integer status, Long id);
}

package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description: 菜品分类接口
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void saveCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .createTime(LocalDateTime.now())
                .createUser(BaseContext.getCurrentId())
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();

        BeanUtils.copyProperties(categoryDTO, category);

        categoryMapper.insert(category);
    }

    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);

        long total = page.getTotal();
        List<Category> list = page.getResult();

        return new PageResult(total, list);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryMapper.deleteCategory(id);
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();

        BeanUtils.copyProperties(categoryDTO, category);

        categoryMapper.update(category);
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .status(status)
                .id(id)
                .build();

        categoryMapper.update(category);


    }
}

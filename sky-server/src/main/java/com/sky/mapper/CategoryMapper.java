package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 菜品分类接口
 */
@Mapper
public interface CategoryMapper {


    void insert(Category category);

    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    @Delete("delete from sky_take_out.category where id = #{id}")
    void deleteCategory(Long id);

    void update(Category category);
}

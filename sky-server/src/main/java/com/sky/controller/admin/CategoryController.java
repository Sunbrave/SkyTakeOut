package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 菜谱分类接口
 */
@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "管理端-菜谱分类接口")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @ApiOperation("新增分类")
    public Result<String> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.saveCategory(categoryDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("菜品分类查询")
    public Result<PageResult> categoryPageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("删除分类")
    public Result<String> deleteCategory(Long id){
        categoryService.deleteCategory(id);

        return Result.success();
    }

    @PutMapping
    @ApiOperation("修改分类")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /**
     * 分类状态变更
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("菜品状态更改")
    public Result<String> startOrStop(@PathVariable Integer status,Long id){
        categoryService.startOrStop(status,id);
        return Result.success();
    }

}

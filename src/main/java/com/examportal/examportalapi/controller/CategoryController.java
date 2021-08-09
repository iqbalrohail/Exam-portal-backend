package com.examportal.examportalapi.controller;

import com.examportal.examportalapi.data.transfer.object.CategoryDto;
import com.examportal.examportalapi.data.transfer.object.MessageDto;
import com.examportal.examportalapi.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
public class CategoryController {

    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public MessageDto addCategory(@RequestBody CategoryDto categoryDto)
    {
        log.info("Post call have been received at category/add with DTO "+categoryDto);
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping("/get/{categoryId}")
    public CategoryDto getCategoryById(@PathVariable("categoryId") int categoryId)
    {
        return categoryService.getCategoryById(categoryId);
    }

    @PutMapping("/update/{categoryId}")
    public MessageDto getCategoryById(@RequestBody CategoryDto categoryDto , @PathVariable("categoryId") int categoryId)
    {
        return categoryService.updateCategoryById(categoryDto , categoryId);
    }

    @DeleteMapping("/delete/{id}")
    public MessageDto deleteCategoryById(@PathVariable("id") int id)
    {
        return categoryService.deleteCategoryById(id);
    }

}

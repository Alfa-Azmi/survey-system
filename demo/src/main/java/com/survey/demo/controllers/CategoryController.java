package com.survey.demo.controllers;

import com.survey.demo.models.surveys.Category;
import com.survey.demo.security.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
//@CrossOrigin("*")

@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;

    //add category

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        logger.info("Add category endpoint reached. Category name: {}", category.getTitle());

        Category category1 = this.categoryService.addCategory(category);
         return ResponseEntity.ok(category1);
    }
    //get category
    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") int categoryId)
    {
        logger.info("Get category by ID endpoint reached. Category ID: {}", categoryId);
        return this.categoryService.getCategory(categoryId);
    }

    //get all categories
    @GetMapping("/")
    public ResponseEntity<?> getCategories(){
        logger.info("Get all categories endpoint reached.");
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    //update category
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category)
    {
        logger.info("Update category endpoint reached. Category ID: {}", category.getCid());
        return this.categoryService.updateCategory(category);
    }

    //delete category
   @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") int categoryId)
    {
        logger.info("Delete category by ID endpoint reached. Category ID: {}", categoryId);
        this.categoryService.deleteCategory(categoryId);
    }

}

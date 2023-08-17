package com.survey.demo.controllers;

import com.survey.demo.models.surveys.Category;
import com.survey.demo.security.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
//@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/category")
//@CrossOrigin("*")

@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //add category
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
         Category category1 = this.categoryService.addCategory(category);
         return ResponseEntity.ok(category1);
    }
    //get category
    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") int categoryId)
    {
        return this.categoryService.getCategory(categoryId);
    }

    //get all categories
    @GetMapping("/")
    public ResponseEntity<?> getCategories(){
       return ResponseEntity.ok(this.categoryService.getCategories());
    }

    //update category
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category)
    {
        return this.categoryService.updateCategory(category);
    }

    //delete category
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") int categoryId)
    {
        this.categoryService.deleteCategory(categoryId);
    }

}

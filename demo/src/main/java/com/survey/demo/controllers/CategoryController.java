package com.survey.demo.controllers;

import com.survey.demo.models.surveys.Category;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.security.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")


@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;

    // Exception handling for addCategory
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        logger.error("An exception occurred: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }

    //To add a category
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        try {
            logger.info("Add category endpoint reached. Category name: {}", category.getTitle());

            Category category1 = this.categoryService.addCategory(category);
            return ResponseEntity.ok(category1);
        } catch (Exception e) {
            logger.error("Error adding category: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //To get a single category
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable("categoryId") int categoryId) {
        try {
            logger.info("Get category by ID endpoint reached. Category ID: {}", categoryId);
            Category category = this.categoryService.getCategory(categoryId);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            logger.error("Error fetching category: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //To get the list of categories
    @GetMapping("/")
    public ResponseEntity<?> getCategories() {
        try {
            logger.info("Get all categories endpoint reached.");
            //List<Category> categories = this.categoryService.getCategories();
            return ResponseEntity.ok(this.categoryService.getCategories());
        } catch (Exception e) {
            logger.error("Error fetching categories: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //To update a category
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        try {
            logger.info("Update category endpoint reached. Category ID: {}", category.getCid());

            Category updatedCategory = this.categoryService.updateCategory(category);
            return ResponseEntity.ok(updatedCategory);
        } catch (Exception e) {
            logger.error("Error updating category: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") int categoryId) {
        try {
            logger.info("Delete category by ID endpoint reached. Category ID: {}", categoryId);
            this.categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok("Category deleted successfully");
        } catch (Exception e) {
            logger.error("Error deleting category: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Search Category
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategories(@RequestParam String keyword) {
        logger.info("search category endpoint reached.");
        try {
            List<Category> searchResults = categoryService.searchCategory(keyword);
            return ResponseEntity.ok(searchResults);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

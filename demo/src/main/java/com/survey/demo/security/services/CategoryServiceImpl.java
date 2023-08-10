package com.survey.demo.security.services;

import com.survey.demo.models.surveys.Category;
import com.survey.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategorySequenceGenerator categorySequenceGenerator;
    @Override
    public Category addCategory(Category category) {
        category.setCid(categorySequenceGenerator.getSequenceNumber((Category.SEQUENCE_NAME)));
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Set<Category> getCategories() {
        return new LinkedHashSet<>(this.categoryRepository.findAll());
    }

    @Override
    public Category getCategory(int categoryId) {
        return this.categoryRepository.findById(categoryId).get();
    }

    @Override
    public void deleteCategory(int categoryId) {
         Category category= new Category();
         category.setCid(categoryId);
        this.categoryRepository.delete(category);

    }
}

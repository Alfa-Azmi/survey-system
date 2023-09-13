package com.survey.demo.security.services;

import com.survey.demo.models.surveys.Category;
import com.survey.demo.models.surveys.Survey;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public Set<Category> getCategories();
    public Category getCategory(int categoryId) ;//categoryId

    public void deleteCategory(int categoryId);

    public List<Category> searchCategory(String keyword);

}

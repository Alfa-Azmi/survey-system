package com.survey.demo.security.services.Impl;

import com.survey.demo.exceptions.CategoryNotFoundException;
import com.survey.demo.models.surveys.Category;
import com.survey.demo.repository.CategoryRepository;
import com.survey.demo.security.services.CategoryService;
import com.survey.demo.security.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Override
    public Category addCategory(Category category) {
        category.setCid(sequenceGeneratorService.getSequenceNumber((Category.SEQUENCE_NAME)));
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
    public Category getCategory (int categoryId) throws CategoryNotFoundException {
        return this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category Not Found with categoryId: " + categoryId));
    }

    @Override
    public void deleteCategory(int categoryId) throws CategoryNotFoundException {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category Not Found with categoryId: " + categoryId));

        this.categoryRepository.delete(category);
    }
}

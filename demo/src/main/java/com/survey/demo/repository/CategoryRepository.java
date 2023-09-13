package com.survey.demo.repository;

import com.survey.demo.models.surveys.Category;
import com.survey.demo.models.surveys.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category,Integer> {

    List<Category> findByTitleContainingIgnoreCase(String title);
}

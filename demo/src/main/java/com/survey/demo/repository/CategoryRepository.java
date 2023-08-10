package com.survey.demo.repository;

import com.survey.demo.models.surveys.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category,Integer> {

}

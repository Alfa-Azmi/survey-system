package com.survey.demo.repository;

import com.survey.demo.models.surveys.Category;
import com.survey.demo.models.surveys.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository extends MongoRepository<Survey,Integer> {
    List<Survey> findBycategory(Category category);

    List<Survey> findByActive(Boolean b);
    List<Survey> findByCategoryAndActive(Category c,Boolean b);


}

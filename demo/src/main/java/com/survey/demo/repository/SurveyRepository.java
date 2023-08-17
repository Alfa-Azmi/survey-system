package com.survey.demo.repository;

import com.survey.demo.models.surveys.Category;
import com.survey.demo.models.surveys.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SurveyRepository extends MongoRepository<Survey,Integer> {
    public List<Survey> findBycategory(Category category);

    public List<Survey> findByActive(Boolean b);
    public List<Survey> findByCategoryAndActive(Category c,Boolean b);


}

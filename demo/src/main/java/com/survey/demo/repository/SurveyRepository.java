package com.survey.demo.repository;

import com.survey.demo.models.surveys.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SurveyRepository extends MongoRepository<Survey,Integer> {

}

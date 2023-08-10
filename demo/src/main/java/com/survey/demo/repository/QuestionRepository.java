package com.survey.demo.repository;

import com.survey.demo.models.surveys.Question;
import com.survey.demo.models.surveys.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

public interface QuestionRepository extends MongoRepository<Question,String> {

    Set<Question> findBySurvey(Survey survey);
}

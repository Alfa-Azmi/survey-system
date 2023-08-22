package com.survey.demo.repository;

import com.survey.demo.models.User;
import com.survey.demo.models.surveys.Result;
import com.survey.demo.models.surveys.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResultRepository extends MongoRepository<Result,Integer> {


    //Result save(Result result);

    List<Result> findBySurveyID(int surveyID);
    List<Result> findAll();
    List<Result> findByUserIDAndSurveyID(int userID,int surveyID);
    List<Result> findByUserID(int userID);
//
//    Result save(Result result);

    //Result save(Result result);
}

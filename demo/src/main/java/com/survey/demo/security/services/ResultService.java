package com.survey.demo.security.services;

import com.survey.demo.models.User;
import com.survey.demo.models.surveys.Result;
import com.survey.demo.models.surveys.Survey;

import java.util.List;

public interface ResultService {

    public Result addResult(Result result);
    public List<Result> getBySurveyID(int surveyID);

    public List<Result> getByUserIDAndSurveyID(int userID,int surveyID);

    public List<Result> getByUserID(int userID);

    //public List<Result> getByUserAndSurvey(int surveyID,int userID);

//    public List<Result> getAllResult();
//    public List<Result> getResultOfSurvey(Survey survey);
//    public List<Result> getResultOfUser(User user);
//    public List<Result> getResultOfUserAndSurvey(Survey survey,User user);
}

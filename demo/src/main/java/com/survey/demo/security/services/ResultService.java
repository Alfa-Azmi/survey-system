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

}

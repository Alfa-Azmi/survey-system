package com.survey.demo.controllers;

import com.survey.demo.models.User;
import com.survey.demo.models.surveys.Result;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.security.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@RestController
@RequestMapping("/api/result")
@CrossOrigin("*")
public class ResultController {

    private static final Logger logger = LoggerFactory.getLogger(ResultController.class);

    @Autowired
    private ResultService resultservice;


    //Add result
    @PostMapping("/")
    public ResponseEntity<?> addResult(@RequestBody Result result)
    {
        logger.info("Add result endpoint reached");
        return ResponseEntity.ok(this.resultservice.addResult(result));
    }

    //Get Result By Survey
    @GetMapping("/survey/{surveyID}")
    public List<Result> getResultBySurvey(@PathVariable("surveyID") int surveyID)
    {
        logger.info("Get result by survey endpoint reached.");
        return resultservice.getBySurveyID(surveyID);

    }

    //Get Result By UserID and SurveyID
    @GetMapping("/{userID}/{surveyID}")
    public List<Result> getResultByUserIDAndSurveyID(@PathVariable("userID") int userID,
                                                 @PathVariable("surveyID") int surveyID)
    {
        logger.info("Get result by user and survey endpoint reached");
        return resultservice.getByUserIDAndSurveyID(userID,surveyID);
    }

    //Get Result By UserID
    @GetMapping("/user/{userID}")
    public List<Result> getResultByUserID(@PathVariable("userID") int userID)
    {
        logger.info("Get result by user endpoint reached");
        return resultservice.getByUserID(userID);

    }
}
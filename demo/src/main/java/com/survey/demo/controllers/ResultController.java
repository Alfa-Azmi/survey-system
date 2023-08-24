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

//    @GetMapping("/{sid}/{uid}")
//    public ResponseEntity<?> getResultByUserAndSurvey(@PathVariable("sid") int sid,@PathVariable("uid") int uid)
//    {
//        Survey survey1=new Survey();
//        survey1.setSId(sid);
//
//        User user1=new User();
//        user1.setId(uid);
//        System.out.println(sid+" "+ uid);
//        List<Result> lis=(this.resultservice.getResultOfUserAndSurvey(survey1, user1));
//        for(Result r:lis)
//        {
//            System.out.println(r.getMarksScored());
//        }
//        return ResponseEntity.ok(lis);
//
//    }
//

    //Get Result By Survey
    @GetMapping("/survey/{surveyID}")
    public List<Result> getResultBySurvey(@PathVariable("surveyID") int surveyID)
    {
        logger.info("Get result by survey endpoint reached.");
        return resultservice.getBySurveyID(surveyID);

    }

    @GetMapping("/{userID}/{surveyID}")
    public List<Result> getResultByUserIDAndSurveyID(@PathVariable("userID") int userID,
                                                 @PathVariable("surveyID") int surveyID)
    {
        logger.info("Get result by user and survey endpoint reached");
        return resultservice.getByUserIDAndSurveyID(userID,surveyID);
    }


    @GetMapping("/user/{userID}")
    public List<Result> getResultByUserID(@PathVariable("userID") int userID)
    {
        logger.info("Get result by user endpoint reached");
        return resultservice.getByUserID(userID);

    }


}
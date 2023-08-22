package com.survey.demo.controllers;

import com.survey.demo.models.User;
import com.survey.demo.models.surveys.Result;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.security.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/result")
@CrossOrigin("*")
public class ResultController {

    @Autowired
    private ResultService resultservice;

    @PostMapping("/")
    public ResponseEntity<?> addResult(@RequestBody Result result)
    {
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
    @GetMapping("/survey/{surveyID}")
    public List<Result> getResultBySurvey(@PathVariable("surveyID") int surveyID)
    {
        return resultservice.getBySurveyID(surveyID);

    }

    @GetMapping("/{userID}/{surveyID}")
    public List<Result> getResultByUserIDAndSurveyID(@PathVariable("userID") int userID,
                                                 @PathVariable("surveyID") int surveyID)
    {
        return resultservice.getByUserIDAndSurveyID(userID,surveyID);
    }


    @GetMapping("/user/{userID}")
    public List<Result> getResultByUserID(@PathVariable("userID") int userID)
    {
        return resultservice.getByUserID(userID);

    }


}
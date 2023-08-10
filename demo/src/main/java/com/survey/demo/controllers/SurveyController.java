package com.survey.demo.controllers;

import com.survey.demo.models.surveys.Category;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.security.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/survey")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    //add survey service
    @PostMapping("/")
    public ResponseEntity<Survey> add(@RequestBody Survey survey)
    {
        //Category category1 = this.categoryService.addCategory(category);

        return ResponseEntity.ok(this.surveyService.addSurvey(survey));
    }

    //update survey
    @PutMapping("/")
    public ResponseEntity<Survey> update(@RequestBody Survey survey)
    {
        return ResponseEntity.ok(this.surveyService.updateSurvey(survey));
    }

    //get survey
    @GetMapping("/")
    public ResponseEntity<?> surveys(){
        return ResponseEntity.ok(this.surveyService.getSurveys());
    }

    //get single survey
    @GetMapping("/{sid}")
    public Survey survey(@PathVariable("sid") int sid)
    {
        return this.surveyService.getSurvey(sid);
    }

    //delete the survey

    @DeleteMapping("/{sid}")
    public void delete(@PathVariable("sid")int sid)
    {
        this.surveyService.deleteSurvey(sid);
    }

}

package com.survey.demo.controllers;

import com.survey.demo.models.surveys.Category;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.security.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/survey")

public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    //add survey service

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Survey> add(@RequestBody Survey survey)
    {
        //Category category1 = this.categoryService.addCategory(category);

        return ResponseEntity.ok(this.surveyService.addSurvey(survey));
    }

    //update survey
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{sid}")
    public void delete(@PathVariable("sid")int sid)
    {
        this.surveyService.deleteSurvey(sid);
    }

    @GetMapping("/category/{cid}")
    public List<Survey> getSurveysOfCategory(@PathVariable("cid") int cid)
    {
        Category category =new Category();
        category.setCid(cid);
       return this.surveyService.getSurveysOfCategory(category);
    }

    //get active surveys
    @GetMapping("/active")
    public List<Survey> getActiveSurveys()
    {
        return this.surveyService.getActiveSurveys();
    }

    //get active surveys of category
    @GetMapping("/category/active/{cid}")
    public List<Survey> getActiveSurveys(@PathVariable("cid") int cid)
    {
        Category category = new Category();
        category.setCid(cid);
        return this.surveyService.getActiveSurveysOfCategory(category);
    }

}

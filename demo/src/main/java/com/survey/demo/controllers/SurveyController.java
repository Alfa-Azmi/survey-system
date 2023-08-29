package com.survey.demo.controllers;

import com.survey.demo.exceptions.SurveyNotFoundException;
import com.survey.demo.models.surveys.Category;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.security.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/survey")

public class SurveyController {
    private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);
    @Autowired
    private SurveyService surveyService;

    //add survey service


    // Exception handling for addCategory
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        logger.error("An exception occurred: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Survey> add(@RequestBody Survey survey)
    {
        //Category category1 = this.categoryService.addCategory(category);
        logger.info("Add survey endpoint reached.");

        return ResponseEntity.ok(this.surveyService.addSurvey(survey));
    }

    //update survey
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<Survey> update(@RequestBody Survey survey)
    {
        logger.info("Update survey endpoint reached.");
        return ResponseEntity.ok(this.surveyService.updateSurvey(survey));
    }

    //get survey
    @GetMapping("/")
    public ResponseEntity<?> surveys(){
        logger.info("Get all the surveys endpoint reached.");
        return ResponseEntity.ok(this.surveyService.getSurveys());
    }

//    //get single survey
//    @GetMapping("/{sid}")
//    public Survey survey(@PathVariable("sid") int sid)
//    {
//        logger.info("Get all the survey endpoint reached.");
//        return this.surveyService.getSurvey(sid);
//    }

    @GetMapping("/{sid}")
    public ResponseEntity<?> survey(@PathVariable("sid") int sid) {
        try {
            logger.info("Get single survey endpoint reached. Survey ID: {}", sid);
            Survey survey = this.surveyService.getSurvey(sid);
            return ResponseEntity.ok(survey);
        } catch (Exception e) {
            logger.error("Error fetching survey: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //delete the survey
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{sid}")
    public void delete(@PathVariable("sid")int sid)
    {
        logger.info("Delete survey endpoint reached.");
        this.surveyService.deleteSurvey(sid);
    }


//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/{sid}")
//    public ResponseEntity<?> delete(@PathVariable("sid") int sid) {
//        try {
//            logger.info("Delete survey endpoint reached.");
//            this.surveyService.deleteSurvey(sid);
//            return ResponseEntity.ok("Survey deleted successfully");
//        } catch (SurveyNotFoundException e) {
//            logger.error("Survey not found: {}", e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Survey not found");
//        } catch (Exception ex) {
//            logger.error("Error deleting survey: {}", ex.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }}

    @GetMapping("/category/{cid}")
    public List<Survey> getSurveysOfCategory(@PathVariable("cid") int cid)
    {
        logger.info("Get surveys of category endpoint reached.");
        Category category =new Category();
        category.setCid(cid);
       return this.surveyService.getSurveysOfCategory(category);
    }

    //get active surveys
    @GetMapping("/active")
    public List<Survey> getActiveSurveys()
    {
        logger.info("Get active surveys endpoint reached.");
        return this.surveyService.getActiveSurveys();
    }

    //get active surveys of category
    @GetMapping("/category/active/{cid}")
    public List<Survey> getActiveSurveys(@PathVariable("cid") int cid)
    {
        logger.info("Get active surveys of category endpoint reached.");
        Category category = new Category();
        category.setCid(cid);
        return this.surveyService.getActiveSurveysOfCategory(category);
    }

}

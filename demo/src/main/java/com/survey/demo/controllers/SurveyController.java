package com.survey.demo.controllers;

import com.survey.demo.exceptions.SurveyNotFoundException;
import com.survey.demo.models.surveys.Category;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.security.services.ResultService;
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
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/survey")
public class SurveyController {
    private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);
    @Autowired
    private SurveyService surveyService;

    // Exception handling for survey
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        logger.error("An exception occurred: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }

    // Add Survey
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Survey> add(@RequestBody Survey survey) {
        try {
            logger.info("Add survey endpoint reached.");
            return ResponseEntity.ok(this.surveyService.addSurvey(survey));
        } catch (Exception e) {
            logger.error("Error adding survey: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Update survey
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<Survey> update(@RequestBody Survey survey) {
        try {
            logger.info("Update survey endpoint reached.");
            return ResponseEntity.ok(this.surveyService.updateSurvey(survey));
        } catch (Exception e) {
            logger.error("Error updating survey: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Get all surveys
    @GetMapping("/")
    public ResponseEntity<Set<Survey>> surveys() {
        try {
            logger.info("Get all the surveys endpoint reached.");
            Set<Survey> surveys = this.surveyService.getSurveys();
            return ResponseEntity.ok(surveys);
        } catch (Exception e) {
            logger.error("Error fetching surveys: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    // Get single survey
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

    // Get surveys of category
    @GetMapping("/category/{cid}")
    public ResponseEntity<List<Survey>> getSurveysOfCategory(@PathVariable("cid") int cid) {
        try {
            logger.info("Get surveys of category endpoint reached.");
            Category category = new Category();
            category.setCid(cid);
            List<Survey> surveys = this.surveyService.getSurveysOfCategory(category);
            return ResponseEntity.ok(surveys);
        } catch (Exception e) {
            logger.error("Error fetching surveys of category: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Get active surveys
    @GetMapping("/active")
    public ResponseEntity<List<Survey>> getActiveSurveys() {
        try {
            logger.info("Get active surveys endpoint reached.");
            List<Survey> surveys = this.surveyService.getActiveSurveys();
            return ResponseEntity.ok(surveys);
        } catch (Exception e) {
            logger.error("Error fetching active surveys: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Get active surveys of category
    @GetMapping("/category/active/{cid}")
    public ResponseEntity<List<Survey>> getActiveSurveys(@PathVariable("cid") int cid) {
        try {
            logger.info("Get active surveys of category endpoint reached.");
            Category category = new Category();
            category.setCid(cid);
            List<Survey> surveys = this.surveyService.getActiveSurveysOfCategory(category);
            return ResponseEntity.ok(surveys);
        } catch (Exception e) {
            logger.error("Error fetching active surveys of category: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Search Survey
    @GetMapping("/search")
    public ResponseEntity<List<Survey>> searchSurveys(@RequestParam String keyword) {
        logger.info("search survey endpoint reached.");
        try {
            List<Survey> searchResults = surveyService.searchSurvey(keyword);
            return ResponseEntity.ok(searchResults);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}

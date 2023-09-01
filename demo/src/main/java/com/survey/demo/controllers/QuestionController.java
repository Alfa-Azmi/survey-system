package com.survey.demo.controllers;

import com.survey.demo.models.surveys.Question;
import com.survey.demo.models.surveys.Result;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.security.services.*;
import com.survey.demo.security.services.Impl.ResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/question")
public class QuestionController {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    private QuestionService service;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private ResultServiceImpl resultService;

    @Autowired
    private SurveyEvaluationService evaluationService;

    //add question
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question)
    {
        logger.info("Add question endpoint reached.");
        return ResponseEntity.ok(this.service.addQuestion(question));
    }

    //update the question
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question)
    {
        logger.info("Update question endpoint reached. Question ID: {}", question.getQuesId());
        return ResponseEntity.ok(this.service.updateQuestion(question));
    }

    //get all question of any survey
    @GetMapping("/survey/{sid}")
    public ResponseEntity<?> getQuestionsOfSurvey(@PathVariable("sid")int sid)
    {
        logger.info("Get questions of survey endpoint reached. Survey ID: {}", sid);
        Survey survey = new Survey();
        survey.setSId(sid);
        Set<Question> questionsOfSurvey = this.service.getQuestionsOfSurvey(survey);
        for (Question question : questionsOfSurvey) {
            question.setAnswer("");
        }
        return ResponseEntity.ok(questionsOfSurvey);

    }

    @GetMapping("/survey/all/{sid}")
    public ResponseEntity<?> getQuestionsOfSurveyAdmin(@PathVariable("sid")int sid)
    {
        logger.info("Get all questions of survey endpoint reached.");
        Survey survey = new Survey();
        survey.setSId(sid);
        Set<Question> questionsOfSurvey = this.service.getQuestionsOfSurvey(survey);
        return ResponseEntity.ok(questionsOfSurvey);

    }

    //get single question
    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") int quesId)
    {
        logger.info("Get a single question of survey endpoint reached.Question ID: {}", quesId);
        return this.service.getQuestion(quesId);
    }
    //delete question
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") int quesId)
    {
       logger.info("Delete question endpoint reached. Question ID: {}", quesId);
        this.service.deleteQuestion(quesId);
    }

    //Evaluate the questions of survey
    @PostMapping("/eval-survey")
    public ResponseEntity<?> evalSurvey(@RequestBody List<Question> questions) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        logger.info(questions.toString());
        ResponseEntity<?> evaluationResult = evaluationService.evalSurvey(questions);

        return evaluationResult; // Simply return the ResponseEntity obtained from the service
    }

}




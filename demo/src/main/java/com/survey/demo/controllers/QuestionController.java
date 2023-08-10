package com.survey.demo.controllers;

import com.survey.demo.models.surveys.Question;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.security.services.QuestionService;
import com.survey.demo.security.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService service;

    @Autowired
    private SurveyService surveyService;

    //add question
    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question)
    {
        return ResponseEntity.ok(this.service.addQuestion(question));
    }

    //update teh question
    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question)
    {
        return ResponseEntity.ok(this.service.updateQuestion(question));
    }

    //get all question of any survey
    @GetMapping("/survey/{sid}")
    public ResponseEntity<?> getQuestionsOfSurvey(@PathVariable("sid")String sid)
    {
        Survey survey = new Survey();
        survey.setSId(sid);
        Set<Question> questionsOfSurvey = this.service.getQuestionsOfSurvey(survey);
        return ResponseEntity.ok(questionsOfSurvey);


//        Survey survey= this.surveyService.getSurvey(sid);
//        Set<Question> questions = survey.getQuestions();
//
//        List list = new ArrayList(questions);
//        if(list.size()>Integer.parseInt(survey.getNumberOfQuestions()))
//        {
//            list=list.subList(0,Integer.parseInt(survey.getNumberOfQuestions()+1));
//
//        }
//        Collections.shuffle(list);
//        return ResponseEntity.ok(list);

    }

    @GetMapping("/survey/all/{sid}")
    public ResponseEntity<?> getQuestionsOfSurveyAdmin(@PathVariable("sid")String sid)
    {
        Survey survey = new Survey();
        survey.setSId(sid);
        Set<Question> questionsOfSurvey = this.service.getQuestionsOfSurvey(survey);
        return ResponseEntity.ok(questionsOfSurvey);

    }

    //get single question
    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") String quesId)
    {
        return this.service.getQuestion(quesId);
    }
    //delete question
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") String quesId)
    {
        this.service.deleteQuestion(quesId);
    }

}

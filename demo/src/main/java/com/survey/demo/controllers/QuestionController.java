package com.survey.demo.controllers;

import com.survey.demo.models.surveys.Question;
import com.survey.demo.models.surveys.Result;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.security.services.*;
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


//        Survey survey = this.surveyService.getSurvey(sid);
//        Set<Question> questions = survey.getQuestions();
//
//        List<Question> list = new ArrayList<>(questions);
//        if (list.size() > Integer.parseInt(survey.getNumberOfQuestions())) {
//            list = list.subList(0, Math.min(list.size(), Integer.parseInt(survey.getNumberOfQuestions())));
//        }
//
//        list.forEach((q) -> {
//            q.setAnswer("");
//        });
//
//        Collections.shuffle(list);
//        return ResponseEntity.ok(list);
//

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

    @PostMapping("/eval-survey")
    public ResponseEntity<?> evalSurvey(@RequestBody List<Question> questions) {
        logger.info("Evaluation of survey endpoint reached");
        int attempted = 0;
        int correctAnswers = 0;
        double marksGot = 0;

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = userDetails.getId();

        int surveyId = 0;

        // Assuming you can somehow get the surveyId from your question or survey data
//        Survey survey = new Survey();
//        int surveyId = surveyService.getActiveSurveys().lastIndexOf();// Replace this with your logic
        //surveyId = q.getSurvey().getSId(); // Use the survey ID from the question


        for(Question q:questions){
            //System.out.println(q.getGivenAnswer());
            //single questions
            Question question = this.service.get(q.getQuesId());
            if(question.getAnswer().equals(q.getGivenAnswer()))
            {
                //correct
                correctAnswers++;

                double marksSingle= Double.parseDouble(questions.get(0).getSurvey().getMaxMarks())/questions.size();
                marksGot += marksSingle;

                //    let marksSingle =this.questions[0].survey.maxMarks/this.questions.length;
                //surveyId = q.getSurvey().getSId();

            } if (q.getGivenAnswer()!=null  )
            {
                attempted++;
            }

            surveyId = q.getSurvey().getSId();

        }

        // Create Result instance and populate it
        Result result = new Result(
                surveyId, // Replace with actual survey ID
                userId, // Replace with actual user ID
                attempted,
                correctAnswers,
                marksGot,
                LocalDateTime.now()
        );

        // Save result using the injected resultService
        Result savedResult = resultService.addResult(result);

        // Prepare response
        Map<String, Object> response = Map.of(
                "marksGot", savedResult.getMarksScored(),
                "correctAnswers", savedResult.getCorrectAns(),
                "attempted", savedResult.getQAttempted()
        );

        return ResponseEntity.ok(response);
    }

//    private double calculateMarksForQuestion(Question question) {
//        // Add your custom logic to calculate marks for a question
//        // For example, you can use question.getMaxMarks() to get max marks for the question
//        // and apply some formula based on whether the answer is correct or not.
//        // Return the calculated marks for this question.
//        return question.getMaxMarks(); // Return max marks for simplicity in this example
//    }
}




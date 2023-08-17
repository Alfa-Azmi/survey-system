package com.survey.demo.controllers;

import com.survey.demo.models.surveys.Question;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.security.services.QuestionService;
import com.survey.demo.security.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<?> getQuestionsOfSurvey(@PathVariable("sid")int sid)
    {
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
        Survey survey = new Survey();
        survey.setSId(sid);
        Set<Question> questionsOfSurvey = this.service.getQuestionsOfSurvey(survey);
        return ResponseEntity.ok(questionsOfSurvey);

    }

    //get single question
    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") int quesId)
    {
        return this.service.getQuestion(quesId);
    }
    //delete question
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") int quesId)
    {
        this.service.deleteQuestion(quesId);
    }

    //eval survey
    @PostMapping("/eval-survey")

    public ResponseEntity<?> evalSurvey(@RequestBody List<Question> questions)
    {
        System.out.println(questions);
        double marksGot=0;
        int correctAnswers=0;
        int attempted=0;

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


            } if (q.getGivenAnswer()!=null  )
            {
                attempted++;
            }
        }

        Map<String, Object> map = Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
        return ResponseEntity.ok(map);
    }
}

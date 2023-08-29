
package com.survey.demo.security.services;

import com.survey.demo.controllers.QuestionController;
import com.survey.demo.models.surveys.Question;
import com.survey.demo.models.surveys.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SurveyEvaluationService {

    private static final Logger logger = LoggerFactory.getLogger(SurveyEvaluationService.class);

    @Autowired
    private  ResultService resultService;

    @Autowired
    private QuestionService service;



    public ResponseEntity<?> evalSurvey(List<Question> questions) {
        logger.info("Evaluation of survey endpoint reached");
        int attempted = 0;
        int correctAnswers = 0;
        double marksGot = 0;

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = userDetails.getId();

        int surveyId = 0;
        String surveyTitle = "";

        for(Question q:questions){
            //single questions
            Question question = this.service.get(q.getQuesId());
            if(question.getAnswer().equals(q.getGivenAnswer()))
            {
                //correct
                correctAnswers++;

                double marksSingle= Double.parseDouble(questions.get(0).getSurvey().getMaxMarks())/questions.size();
                marksGot += marksSingle;

            } if (q.getGivenAnswer()!=null  )
            {
                attempted++;
            }

            surveyId = q.getSurvey().getSId();

        }

        // Create Result instance and populate it
        Result result = new Result(
                surveyId,
                userId,
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
}

package com.survey.demo.security.services;

import com.survey.demo.models.surveys.Question;
import com.survey.demo.models.surveys.Survey;

import java.util.Set;

public interface QuestionService {

    public Question addQuestion(Question question);
    public Question updateQuestion(Question question);
    public Set<Question> getQuestions();
    public Question getQuestion(int questionId);

    public Set<Question> getQuestionsOfSurvey(Survey survey);

    public void deleteQuestion(int quesId);

}

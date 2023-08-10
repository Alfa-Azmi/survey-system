package com.survey.demo.security.services;

import com.survey.demo.models.surveys.Question;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Question addQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestion(String questionId) {
        return this.questionRepository.findById(questionId).get();
    }

    @Override
    public Set<Question> getQuestionsOfSurvey(Survey survey) {
        return this.questionRepository.findBySurvey(survey);
    }

    @Override
    public void deleteQuestion(String quesId) {
        Question question = new Question();
        question.setQuesId(quesId);
        this.questionRepository.delete(question);
    }
}

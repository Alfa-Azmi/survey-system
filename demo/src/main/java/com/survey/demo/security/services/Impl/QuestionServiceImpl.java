package com.survey.demo.security.services.Impl;

import com.survey.demo.models.surveys.Question;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.repository.QuestionRepository;
import com.survey.demo.security.services.QuestionService;
import com.survey.demo.security.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

//    @Autowired
//    private QuestionSequenceGenerator questionSequenceGenerator;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Question addQuestion(Question question) {
        //survey.setSId(surveySequenceGenerator.getSequenceNumber((Survey.SEQUENCE_NAME)));
        question.setQuesId(sequenceGeneratorService.getSequenceNumber((Question.SEQUENCE_NAME)));
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
    public Question getQuestion(int questionId) {
        return this.questionRepository.findById(questionId).get();
    }

    @Override
    public Set<Question> getQuestionsOfSurvey(Survey survey) {
        return this.questionRepository.findBySurvey(survey);
    }

    @Override
    public void deleteQuestion(int quesId) {
        Question question = new Question();
        question.setQuesId(quesId);
        this.questionRepository.delete(question);
    }

    @Override
    public Question get(int questionsId) {
        return this.questionRepository.findById(questionsId).orElse(null);
    }
}

package com.survey.demo.models.surveys;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "question")
public class Question {
    @Transient
    public static String SEQUENCE_NAME = "question_sequence";
    @Id
    private int quesId;
    private String content;
    private String image;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    //@ManyToOne(fetch = FetchType.EAGER)
    @DBRef// Use DBRef to create a reference to the Survey document
    private Survey survey;

}

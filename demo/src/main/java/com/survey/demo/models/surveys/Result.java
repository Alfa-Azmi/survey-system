package com.survey.demo.models.surveys;

import com.survey.demo.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "results")
public class Result {

    @Transient
    public static String SEQUENCE_NAME = "result_sequence";
    @Id
    private int resultId;

    private int surveyID;

//    @DBRef
//    private Survey survey;

    private int userID;

    private int qAttempted;
    private int correctAns;
    private double marksScored;
    private LocalDateTime submitDateTime;

    public Result(int surveyID, int userID, int qAttempted, int correctAns, double marksScored, LocalDateTime submitDateTime) {
        this.surveyID = surveyID;
        this.userID = userID;
        this.qAttempted = qAttempted;
        this.correctAns = correctAns;
        this.marksScored = marksScored;
        this.submitDateTime = LocalDateTime.now();
    }
}

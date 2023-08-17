package com.survey.demo.models.surveys;

import com.survey.demo.models.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "results") // Specify the collection name
//public class Result {
//
//    @Id
//    private String result_id;
//    @DBRef
//    private Survey survey; // Assuming you have a Quiz class as well
//    @DBRef
//
//    private User user; // Assuming you have a User class as well
//    private int qAttempted;
//    private int correctAns;
//    private double marksScored;
//    private String submitDateTime;
//
//    // Constructors
//    public Result() {
//    }
//
//    public Result(Survey survey, User user, int qAttempted, int correctAns, double marksScored, String submitDateTime) {
//        this.survey = survey;
//        this.user = user;
//        this.qAttempted = qAttempted;
//        this.correctAns = correctAns;
//        this.marksScored = marksScored;
//        this.submitDateTime = submitDateTime;
//    }
//
//    // Getters and Setters
//    // ...
//
//    // Other methods
//    // ...
//}

package com.survey.demo.exceptions;

public class SurveyNotFoundException extends RuntimeException {

    public SurveyNotFoundException(String msg){
        super(msg);
    }

    public SurveyNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

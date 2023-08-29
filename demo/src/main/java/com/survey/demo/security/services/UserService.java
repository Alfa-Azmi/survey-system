package com.survey.demo.security.services;

import com.survey.demo.models.User;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.payload.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface UserService {

    public List<UserResponse> getUsers();
}

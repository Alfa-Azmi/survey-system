package com.survey.demo.security.services;

import com.survey.demo.models.User;
import com.survey.demo.models.surveys.Survey;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface UserService {

    public Set<User> getUsers();
}

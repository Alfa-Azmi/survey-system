package com.survey.demo.security.services;

import com.survey.demo.models.User;
import com.survey.demo.repository.SurveyRepository;
import com.survey.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
        @Override
        public Set<User> getUsers() {
        return new HashSet<>(this.userRepository.findAll());
    }
}

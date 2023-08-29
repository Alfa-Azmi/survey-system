package com.survey.demo.security.services.Impl;

import com.survey.demo.models.User;
import com.survey.demo.payload.response.UserResponse;
import com.survey.demo.repository.SurveyRepository;
import com.survey.demo.repository.UserRepository;
import com.survey.demo.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
//        @Override
//        public Set<User> getUsers() {
//        return new HashSet<>(this.userRepository.findAll());
//    }

    public List<UserResponse> getUsers(){
        List<User> users = this.userRepository.findAll();
        List<UserResponse> updatedUsers = new ArrayList<>();

        for(User user: users){
           updatedUsers.add(new UserResponse(user.getId(), user.getFirstName(), user.getLastName())) ;
        }

        return updatedUsers;
    }


}

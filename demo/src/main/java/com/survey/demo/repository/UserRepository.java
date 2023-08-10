package com.survey.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.survey.demo.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByFirstName(String firstName);

    Boolean existsByLastName(String lastName);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);
}

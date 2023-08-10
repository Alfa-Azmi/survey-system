package com.survey.demo.payload.response;

import java.util.List;

import lombok.Data;


@Data


public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<String> roles;


    public JwtResponse(String accessToken, int id, String username, String firstName,
                       String lastName, String email, String phone, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
    }


}


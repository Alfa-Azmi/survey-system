package com.survey.demo.payload.request;

import java.util.Set;

import jakarta.validation.constraints.*;

import lombok.Data;
//import lombok.NoArgsConstructor;

@Data

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 10)
    private String phone;

    private Set<String> roles;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;


}

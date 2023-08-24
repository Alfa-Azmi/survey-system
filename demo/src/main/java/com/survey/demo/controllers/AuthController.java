package com.survey.demo.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.survey.demo.security.services.SequenceGeneratorService;
import com.survey.demo.security.services.SurveyService;
import com.survey.demo.security.services.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.survey.demo.models.ERole;
import com.survey.demo.models.Role;
import com.survey.demo.models.User;
import com.survey.demo.payload.request.LoginRequest;
import com.survey.demo.payload.request.SignupRequest;
import com.survey.demo.payload.response.JwtResponse;
import com.survey.demo.payload.response.MessageResponse;
import com.survey.demo.repository.RoleRepository;
import com.survey.demo.repository.UserRepository;
import com.survey.demo.security.jwt.JwtUtils;
import com.survey.demo.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private SequenceGeneratorService service;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("The /signin endpoint has been reached");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        logger.info("User {} has successfully logged in", loginRequest.getUsername());
        logger.info("Token successfully generated");

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getEmail(),
                userDetails.getPhone(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        logger.info("The /signup endpoint has been reached");
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            logger.error("Username is already taken");
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            logger.error("Email is already in use");
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getEmail(),
                signUpRequest.getPhone()
                );

        logger.info("Password has been encoded");

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            logger.info("The user has been assigned the role of ROLE_USER");
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        logger.info("The user has been assigned the role of ADMIN");
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        logger.info("The user has been assigned the role of ROLE_USER");
                        roles.add(userRole);
                }
            });
        }
        user.setId(service.getSequenceNumber(User.SEQUENCE_NAME));
        user.setRoles(roles);
        logger.info("All roles have been assigned to the user");
        userRepository.save(user);
        logger.info("User successfully saved to the database");
        logger.info("User registered successfully!");

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    //get user
    @GetMapping("/")
    public ResponseEntity<?> users(){
        logger.info("Get all the users endpoint reached.");
        return ResponseEntity.ok(this.userService.getUsers());
    }
}


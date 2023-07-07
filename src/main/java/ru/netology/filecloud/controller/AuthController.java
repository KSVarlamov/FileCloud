package ru.netology.filecloud.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.netology.filecloud.dto.AuthToken;
import ru.netology.filecloud.dto.Error;
import ru.netology.filecloud.dto.LoginMessage;
import ru.netology.filecloud.model.User;
import ru.netology.filecloud.repository.UserRepository;
import ru.netology.filecloud.security.services.jwt.JwtUtils;

@RestController
@RequestMapping("/")
@Slf4j
@CrossOrigin
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginMessage loginRequest) {

        log.info("Получен запрос на авторизацию {} ", loginRequest);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.login(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(new AuthToken(jwtUtils.generateJwtToken(authentication)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody LoginMessage signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.login())) {
            return ResponseEntity
                    .badRequest()
                    .body(new Error("Error: Username is already taken!", 1));
        }

        // Create new user's account
        User user = new User(signUpRequest.login(),
                encoder.encode(signUpRequest.password()));

        userRepository.save(user);

        return ResponseEntity.ok("Registered");
    }


}

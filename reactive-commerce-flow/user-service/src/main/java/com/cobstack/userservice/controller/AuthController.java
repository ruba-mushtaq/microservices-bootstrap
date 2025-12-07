package com.cobstack.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobstack.userservice.model.User;
import com.cobstack.userservice.repository.UserRepository;
import com.cobstack.userservice.security.JwtUtil;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public Mono<User> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null) user.setRoles("USER");
        return userRepository.save(user);
    }

    @PostMapping("/login")
    public Mono<String> login(@RequestBody User loginUser) {
        return userRepository.findByUsername(loginUser.getUsername())
                .flatMap(user -> {
                    if (passwordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
                        return Mono.just(jwtUtil.generateToken(user.getUsername()));
                    } else {
                        return Mono.error(new RuntimeException("Invalid credentials"));
                    }
                });
    }
}

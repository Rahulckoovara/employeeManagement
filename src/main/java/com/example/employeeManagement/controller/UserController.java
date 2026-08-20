package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.request.CreateUserRequestDto;
import com.example.employeeManagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> createUser(
            @Valid
            @RequestBody CreateUserRequestDto dto){

        userService.createUser(dto);

        return ResponseEntity.ok("User created successfully");
    }
}

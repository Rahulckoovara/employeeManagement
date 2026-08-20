package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.request.CreateUserRequestDto;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.entity.User;
import com.example.employeeManagement.exception.ResourceNotFoundException;
import com.example.employeeManagement.repo.EmployeeRepository;
import com.example.employeeManagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(CreateUserRequestDto dto) {

        if(userRepository.existsByUsername(dto.getUsername())){
            throw new RuntimeException("Username already exists");
        }

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found"));

        User user = new User();

        user.setUsername(dto.getUsername());

        user.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        user.setRole(dto.getRole());

        user.setEmployee(employee);

        user.setEnabled(true);

        userRepository.save(user);

    }
}

package com.example.employeeManagement.dto.request;

import com.example.employeeManagement.util.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserRequestDto {

    @NotBlank
    private String username;


    @NotBlank
    private String password;

    @NotNull
    private Role role;

    @NotNull
    private Long employeeId;

}
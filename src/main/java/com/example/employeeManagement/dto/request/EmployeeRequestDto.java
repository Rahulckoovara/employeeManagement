package com.example.employeeManagement.dto.request;

import com.example.employeeManagement.util.EmployeeStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be greater than zero")
    private BigDecimal salary;

    @NotBlank(message = "Designation is required")
    private String designation;


    @NotNull(message = "Hire date is required")
    private LocalDate hireDate;

    @NotNull(message = "Status is required")
    private EmployeeStatus status;

    @NotNull(message = "Department is required")
    private Long departmentId;
}

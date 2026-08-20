package com.example.employeeManagement.dto.response;

import com.example.employeeManagement.util.EmployeeStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonPropertyOrder({ "id", "name", "email", "phone", "salary", "designation","hireDate","status","departmentName" })
public class EmployeeResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private BigDecimal salary;
    private String designation;
    private LocalDate hireDate;
    private EmployeeStatus status;
    private String departmentName;
}
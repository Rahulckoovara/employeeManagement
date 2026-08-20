package com.example.employeeManagement.mapper;

import com.example.employeeManagement.dto.request.EmployeeRequestDto;
import com.example.employeeManagement.dto.response.EmployeeResponseDto;
import com.example.employeeManagement.entity.Department;
import com.example.employeeManagement.entity.Employee;


public class EmployeeMapper {
    public static Employee toEntity(EmployeeRequestDto dto,
                                    Department department) {

        Employee employee = new Employee();

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setSalary(dto.getSalary());
        employee.setDesignation(dto.getDesignation());
        employee.setHireDate(dto.getHireDate());
        employee.setStatus(dto.getStatus());

        employee.setDepartment(department);

        return employee;
    }

    public static EmployeeResponseDto toResponse(Employee employee) {

        EmployeeResponseDto dto = new EmployeeResponseDto();

        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setSalary(employee.getSalary());
        dto.setDesignation(employee.getDesignation());
        dto.setHireDate(employee.getHireDate());
        dto.setStatus(employee.getStatus());

        dto.setDepartmentName(
                employee.getDepartment() != null ? employee.getDepartment().getName() : null
        );

        return dto;
    }
}

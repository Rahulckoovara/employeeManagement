package com.example.employeeManagement.mapper;

import com.example.employeeManagement.dto.request.DepartmentRequestDto;
import com.example.employeeManagement.dto.response.DepartmentResponseDto;
import com.example.employeeManagement.entity.Department;

public class DepartmentMapper {

    public static Department toEntity(DepartmentRequestDto dto) {
        Department department = new Department();
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        return department;
    }

    public static DepartmentResponseDto toResponse(Department department) {
        DepartmentResponseDto dto = new DepartmentResponseDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setDescription(department.getDescription());
        dto.setCreatedAt(department.getCreatedAt());
        dto.setUpdatedAt(department.getUpdatedAt());
        dto.setEmployeeCount(
                department.getEmployees() != null ? department.getEmployees().size() : 0
        );
        return dto;
    }
}

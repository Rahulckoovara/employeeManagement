package com.example.employeeManagement.service;

import com.example.employeeManagement.dto.request.DepartmentRequestDto;
import com.example.employeeManagement.dto.response.DepartmentResponseDto;
import com.example.employeeManagement.entity.Department;
import com.example.employeeManagement.exception.ResourceNotFoundException;
import com.example.employeeManagement.mapper.DepartmentMapper;
import com.example.employeeManagement.repo.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository depRepo;

    // Create
    public DepartmentResponseDto createDepartment(DepartmentRequestDto dto) {
        Department department = DepartmentMapper.toEntity(dto);
        Department saved = depRepo.save(department);
        return DepartmentMapper.toResponse(saved);
    }

    // Get All
    public List<DepartmentResponseDto> getAllDepartments() {
        return depRepo.findAllWithEmployees()
                .stream()
                .map(DepartmentMapper::toResponse)
                .toList();
    }

    // Get By Id
    public DepartmentResponseDto getDepartmentById(Long id) {
        Department department = depRepo.findByIdWithEmployees(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        return DepartmentMapper.toResponse(department);
    }

    // Update
    public DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto dto) {
        Department existing = depRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());

        Department updated = depRepo.save(existing);
        return DepartmentMapper.toResponse(updated);
    }

    // Delete
    public void deleteDepartment(Long id) {
        if (!depRepo.existsById(id)) {
            throw new ResourceNotFoundException("Department not found with id: " + id);
        }
        depRepo.deleteById(id);
    }
}

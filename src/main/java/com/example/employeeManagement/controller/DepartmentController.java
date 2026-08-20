package com.example.employeeManagement.controller;

import com.example.employeeManagement.dto.request.DepartmentRequestDto;
import com.example.employeeManagement.dto.response.DepartmentResponseDto;
import com.example.employeeManagement.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // POST /api/depts
    @PostMapping("/depts")
    public ResponseEntity<DepartmentResponseDto> createDepartment(
            @Valid
            @RequestBody DepartmentRequestDto dto) {
        DepartmentResponseDto response = departmentService.createDepartment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/depts
    @GetMapping("/depts")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    // GET /api/depts/{id}
    @GetMapping("/depts/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    // PUT /api/depts/{id}
    @PutMapping("/depts/{id}")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(
            @PathVariable Long id,
            @Valid
            @RequestBody DepartmentRequestDto dto) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, dto));
    }

    // DELETE /api/depts/{id}
    @DeleteMapping("/depts/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department deleted successfully");
    }
}


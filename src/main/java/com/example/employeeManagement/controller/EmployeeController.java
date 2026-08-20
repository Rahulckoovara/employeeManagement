package com.example.employeeManagement.controller;


import com.example.employeeManagement.dto.request.EmployeeRequestDto;
import com.example.employeeManagement.dto.response.EmployeeResponseDto;
import com.example.employeeManagement.dto.response.PageResponseDto;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.service.DepartmentService;
import com.example.employeeManagement.service.EmployeeService;
import com.example.employeeManagement.util.EmployeeStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Employee", description = "Employee Management APIs")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity<EmployeeResponseDto> createEmployee(
            @Valid
            @RequestBody EmployeeRequestDto dto) {
        EmployeeResponseDto response = employeeService.createEmployee(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/employeess")
    public PageResponseDto<EmployeeResponseDto> getAllEmployees(  @RequestParam  (required = false) String search,
                                                                  @RequestParam (required = false)
                                                                  Long departmentId,
                                                                  @RequestParam (required = false)
                                                                      EmployeeStatus status,

                                                                  @RequestParam (defaultValue = "0") int page,
                                                                 @RequestParam (defaultValue = "5") int size,
                                                                @RequestParam (defaultValue = "id") String sortBy,
                                                                @RequestParam(defaultValue = "asc") String sortDir
                                                                     ) {
        return employeeService.getAllEmployees(search,departmentId,status, page, size,sortBy,sortDir);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
        EmployeeResponseDto response = employeeService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable Long id,
            @Valid
            @RequestBody EmployeeRequestDto dto) {
        EmployeeResponseDto response = employeeService.updateEmployee(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee Deleted Successfully");
    }
}

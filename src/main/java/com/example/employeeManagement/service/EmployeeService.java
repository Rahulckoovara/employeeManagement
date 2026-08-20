package com.example.employeeManagement.service;

import com.example.employeeManagement.Specification.EmployeeSpecification;
import com.example.employeeManagement.dto.request.EmployeeRequestDto;
import com.example.employeeManagement.dto.response.EmployeeResponseDto;
import com.example.employeeManagement.dto.response.PageResponseDto;
import com.example.employeeManagement.entity.Department;
import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.exception.ResourceNotFoundException;
import com.example.employeeManagement.mapper.EmployeeMapper;
import com.example.employeeManagement.repo.DepartmentRepository;
import com.example.employeeManagement.repo.EmployeeRepository;
import com.example.employeeManagement.util.EmployeeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository depRepository;

    public EmployeeResponseDto createEmployee(EmployeeRequestDto dto) {
        Department department = depRepository
                .findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + dto.getDepartmentId()));
        Employee employee =
                EmployeeMapper.toEntity(dto, department);
        Employee savedEmployee =
                employeeRepository.save(employee);
        return EmployeeMapper.toResponse(savedEmployee);
    }

    public PageResponseDto<EmployeeResponseDto> getAllEmployees(String search, Long departmentId, EmployeeStatus status, int page, int size, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page,size,sort);
        Specification<Employee> specification =
                Specification.where(
                                EmployeeSpecification.hasKeyword(search))
                        .and(EmployeeSpecification.hasDepartment(departmentId))
                        .and(EmployeeSpecification.hasStatus(status));


        Page<Employee> employeePage= employeeRepository.findAll(specification,
                pageable);

        List<EmployeeResponseDto>employees=employeePage.getContent().stream().map(EmployeeMapper::toResponse).toList();

        return PageResponseDto.<EmployeeResponseDto>builder()
                .content(employees)
                .pageNumber(employeePage.getNumber())
                .pageSize(employeePage.getSize())
                .totalElements(employeePage.getTotalElements())
                .totalPages(employeePage.getTotalPages())
                .last(employeePage.isLast())
                .build();
//        return employeeRepository.findAllWithDepartment().stream()
//                .map(EmployeeMapper::toResponse)
//                .toList();
    }

    //getById
    public EmployeeResponseDto  getUserById(Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        return EmployeeMapper.toResponse(employee);
    }

    public EmployeeResponseDto updateEmployee(Long id,
                                              EmployeeRequestDto dto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        Department department = depRepository
                .findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + dto.getDepartmentId()));

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setSalary(dto.getSalary());
        employee.setDesignation(dto.getDesignation());
        employee.setHireDate(dto.getHireDate());
        employee.setStatus(dto.getStatus());
        employee.setDepartment(department);

        Employee updatedEmployee =
                employeeRepository.save(employee);

        return EmployeeMapper.toResponse(updatedEmployee);

    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}

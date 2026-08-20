package com.example.employeeManagement.repo;

import com.example.employeeManagement.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.department")
    List<Employee> findAllWithDepartment();

  //  Page<Employee> findByNameContainingIgnoreCase(String name,Pageable pageable);

    @Query("""
SELECT e FROM Employee e WHERE
LOWER(e.name)
LIKE LOWER(CONCAT('%',:search,'%'))
OR
LOWER(e.email)
LIKE LOWER(CONCAT('%',:search,'%'))
OR
LOWER(e.designation)
LIKE LOWER(CONCAT('%',:search,'%'))
""")
    Page<Employee> searchEmployee(
            @Param("search") String keyword,
            Pageable pageable);


}

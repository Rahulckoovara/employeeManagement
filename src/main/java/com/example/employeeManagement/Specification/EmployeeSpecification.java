package com.example.employeeManagement.Specification;

import com.example.employeeManagement.entity.Employee;
import com.example.employeeManagement.util.EmployeeStatus;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {
    public static Specification<Employee> hasKeyword(String search) {
        return (root, query, cb) -> {
            if (search == null || search.isBlank()) {
                return cb.conjunction();
            }
            String pattern = "%" + search.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("name")), pattern),
                    cb.like(cb.lower(root.get("email")), pattern),
                    cb.like(cb.lower(root.get("designation")), pattern)
            );
        };

    }
    public static Specification<Employee> hasDepartment(Long departmentId) {

        return (root, query, cb) -> {

            if (departmentId == null) {
                return cb.conjunction();
            }

            return cb.equal(
                    root.get("department").get("id"),
                    departmentId);

        };

    }

    public static Specification<Employee> hasStatus(EmployeeStatus status) {

        return (root, query, cb) -> {

            if (status == null) {
                return cb.conjunction();
            }

            return cb.equal(root.get("status"), status);

        };

    }


}

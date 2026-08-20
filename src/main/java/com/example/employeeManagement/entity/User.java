package com.example.employeeManagement.entity;

import com.example.employeeManagement.util.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean enabled=true;

    @OneToOne
    @JoinColumn(name = "employee_id", unique = true)
    private Employee employee;


}

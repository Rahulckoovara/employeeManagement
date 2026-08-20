package com.example.employeeManagement.config;

import com.example.employeeManagement.entity.User;
import com.example.employeeManagement.repo.UserRepo;
import com.example.employeeManagement.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.count() == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            admin.setEnabled(true);
            userRepo.save(admin);
            System.out.println("=================================================");
            System.out.println("Default admin user created: admin / admin123");
            System.out.println("=================================================");
        }
    }
}

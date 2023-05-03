package com.javaproject.javaspring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaproject.javaspring.models.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    
}

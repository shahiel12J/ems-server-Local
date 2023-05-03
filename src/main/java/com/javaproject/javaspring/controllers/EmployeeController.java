package com.javaproject.javaspring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject.javaspring.models.Employee;
import com.javaproject.javaspring.repo.EmployeeRepo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "http://http://localhost:4200/")
@RequestMapping(value = "/employee")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping(value = "/getEmployees")
    public List < Employee > getAll() {
        return employeeRepo.findAll();
    }

    @GetMapping(value = "/getEmployee/{empId}")
    public Employee getEmployee(@PathVariable int empId) {
        return employeeRepo.findById(empId).orElseThrow(() -> new IllegalStateException("Employee with id " + empId + " does not exist"));
    }

    @PostMapping(value="/addEmployee")
    public  ResponseEntity<String> addEmployee(@Validated @NonNull @RequestBody Employee newEmployee) {
        employeeRepo.save(newEmployee);
        return new ResponseEntity<String>("Employee has successfully been added!", HttpStatus.CREATED);
    }
    
    @PutMapping(value="/updateEmployee/{empId}")
    public ResponseEntity<String> updateEmployee(@PathVariable int empId, @Validated @NonNull @RequestBody Employee updateEmployee) {

        Employee employee = employeeRepo.findById(empId).orElseThrow(() -> new IllegalStateException("Employee with id " + empId + " does not exist"));
        employee.setEmpName(updateEmployee.getEmpName());
        employee.setEmpLastName(updateEmployee.getEmpLastName());
        employee.setCellNumber(updateEmployee.getCellNumber());
        employee.setEmail(updateEmployee.getEmail());
        employee.setRole(updateEmployee.getRole());
        employee.setSalary(updateEmployee.getSalary());
        employeeRepo.save(employee);
        return new ResponseEntity<>("Employee with id " + empId +" has been updated", HttpStatus.OK);
    }

    @DeleteMapping(value="/deleteEmployee/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int empId) {
        employeeRepo.deleteById(empId);
        return new ResponseEntity<String>("Employee with id " + empId + " has been deleted", HttpStatus.OK);
    }

}

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

import com.javaproject.javaspring.exception.BadRequestException;
import com.javaproject.javaspring.exception.EmployeeExistException;
import com.javaproject.javaspring.exception.EmployeeNotFound;
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
    public Employee getEmployee(@PathVariable int empId) throws EmployeeNotFound, BadRequestException{
        if (empId <= 0){
            throw new BadRequestException("Please use a valid employee ID");
        }
        return employeeRepo.findById(empId).orElseThrow(() -> new EmployeeNotFound("Employee with id " + empId + " does not exist"));
    }

    @PostMapping(value="/addEmployee")
    public  ResponseEntity<String> addEmployee( @Validated @NonNull @RequestBody Employee newEmployee) throws EmployeeExistException, EmployeeNotFound, BadRequestException {
        if (newEmployee == null){
            throw new BadRequestException("Please have a valid request body");
        }
        List<Employee> employees = employeeRepo.findAll();
        for (Employee e: employees){
            if(e.getEmpId() == newEmployee.getEmpId()){
                throw new EmployeeExistException("This employee already exists");
            }
        }
        employeeRepo.save(newEmployee);
        return new ResponseEntity<String>("Employee has successfully been added!", HttpStatus.CREATED);
    }
    
    @PutMapping(value="/updateEmployee/{empId}")
    public ResponseEntity<String> updateEmployee(@PathVariable int empId, @Validated @NonNull @RequestBody Employee updateEmployee) throws EmployeeNotFound, BadRequestException {
        if (empId <= 0){
            throw new BadRequestException("Please enter a valid employee ID");
        }
        if (updateEmployee == null){
            throw new BadRequestException("Please have a valid request body");
        }
        Employee employee = employeeRepo.findById(empId).orElseThrow(() -> new EmployeeNotFound("Employee with id " + empId + " does not exist"));
        if (employee != null){
            employee.setEmpNumber(updateEmployee.getEmployeeNumber());
            employee.setEmpName(updateEmployee.getEmpName());
            employee.setEmpLastName(updateEmployee.getEmpLastName());
            employee.setCellNumber(updateEmployee.getCellNumber());
            employee.setEmail(updateEmployee.getEmail());
            employee.setRole(updateEmployee.getRole());
            employee.setSalary(updateEmployee.getSalary());
            employeeRepo.save(employee);
        }
        return new ResponseEntity<>("Employee with id " + empId +" has been updated", HttpStatus.OK);
    }

    @DeleteMapping(value="/deleteEmployee/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int empId) throws BadRequestException{
        if (empId <= 0){
            throw new BadRequestException("Please enter a valid employee ID");
        }
        employeeRepo.deleteById(empId);
        return new ResponseEntity<String>("Employee with id " + empId + " has been deleted", HttpStatus.OK);
    }

}

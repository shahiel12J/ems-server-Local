package com.javaproject.javaspring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int empId;
    @Column(name = "employee_number")
    public int employeeNumber;
    @Column(name = "first_name")
    public String empName;
    @Column(name = "last_name")
    public String empLastName;
    @Column(name = "cell_number")
    public String cellNumber;
    @Column(name = "email")
    public String email;
    @Column(name = "emp_role")
    public String role;
    @Column(name = "salary")
    public int salary;

    public Employee(int empId,int employeeNumber, String empName, String empLastName, String cellNumber, String email, String role, int salary) {
        this.empId = empId;
        this.employeeNumber =employeeNumber;
        this.empName = empName;
        this.empLastName = empLastName;
        this.cellNumber = cellNumber;
        this.email = email;
        this.role = role;
        this.salary = salary;
    }

    public int getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

     public int getEmployeeNumber(){
        return employeeNumber;
     }  
    

    public String getEmpLastName() {
        return empLastName;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public int getSalary() {
        return salary;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setEmpNumber(int employeeNumber){
        this.employeeNumber = employeeNumber;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
        
    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean equals(Object obj){
        if(this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Employee)) return false;
        Employee other = (Employee) obj;
        if (empId == 0) {
            if (other.empId != 0) return false;
        } else if (!(empId == other.empId)) return false;
        return true;
    }
}

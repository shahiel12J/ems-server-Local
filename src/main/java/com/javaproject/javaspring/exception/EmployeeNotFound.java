package com.javaproject.javaspring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value = HttpStatus.NOT_FOUND)


public class EmployeeNotFound extends Exception{
    private static final long serialVersionUID = 1;
    public EmployeeNotFound (String message) {
    super(message);   
    }
    
}

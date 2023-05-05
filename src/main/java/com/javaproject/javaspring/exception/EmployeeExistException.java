package com.javaproject.javaspring.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value = HttpStatus.CONFLICT)

public class EmployeeExistException extends Exception {

    private static final long serialVersionUID = 1;
    public EmployeeExistException (String message) {
    super(message);   




  }
}

package com.example.expensetracker.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class EtConvertFail extends RuntimeException{

    public EtConvertFail(String message){
        super(message);
    }
}

package com.itpatagonia.microservices.studentmicroservice.Exceptions;

public class NoEntityException extends Exception{
    public NoEntityException(String message){
        super(message);
    }
}

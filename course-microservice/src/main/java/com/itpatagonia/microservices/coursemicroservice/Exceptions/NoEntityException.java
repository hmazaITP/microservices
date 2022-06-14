package com.itpatagonia.microservices.coursemicroservice.Exceptions;

public class NoEntityException extends Exception{
    public NoEntityException(String message){
        super(message);
    }
}

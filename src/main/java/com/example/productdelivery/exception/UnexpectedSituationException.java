package com.example.productdelivery.exception;


//Custom unexpected situation exception
public class UnexpectedSituationException extends RuntimeException {
    public UnexpectedSituationException(String message) {
        super(message);
    }
}
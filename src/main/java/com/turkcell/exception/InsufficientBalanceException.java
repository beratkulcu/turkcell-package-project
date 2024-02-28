package com.turkcell.exception;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(String message) {

        super(message);

    }
}

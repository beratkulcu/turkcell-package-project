package com.turkcell.exception;

public class IllegalLogin extends RuntimeException{

    public IllegalLogin(String message) {
        super(message);
    }
}

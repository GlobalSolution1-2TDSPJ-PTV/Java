package com.global.FloodWatch.exception;

public class UserUnauthorized extends RuntimeException {
    public UserUnauthorized(String message) {
        super(message);
    }
}

package com.example.audit.exception;

public class ForbiddenException extends RuntimeException {
    @Override
    public String getMessage() {
        return "{forbidden.message}";
    }
}

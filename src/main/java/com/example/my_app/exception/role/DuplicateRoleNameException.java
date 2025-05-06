package com.example.my_app.exception.role;

public class DuplicateRoleNameException extends RuntimeException {
    public DuplicateRoleNameException(String message) {
        super(message);
    }
}

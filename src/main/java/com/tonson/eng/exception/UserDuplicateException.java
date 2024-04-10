package com.tonson.eng.exception;

public class UserDuplicateException extends RuntimeException {
    public UserDuplicateException(String username) {
        super("Username: " + username + " alredy exists.");
    }
}

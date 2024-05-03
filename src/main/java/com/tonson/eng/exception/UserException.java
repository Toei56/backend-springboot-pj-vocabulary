package com.tonson.eng.exception;

public class UserException extends BaseException {
    public UserException(String message) {
        super("user." + message);
    }

    public static UserException createEmailDuplicate() {
        return new UserException("create.email.duplicate");
    }

    public static UserException loginFailEmailNotFound() {
        return new UserException("login.fail");
    }

    public static UserException loginFailPasswordIncorrect() {
        return new UserException("login.fail");
    }

    public static UserException notFound() {
        return new UserException("user.not.found");
    }

    public static UserException unauthorized() {
        return new UserException("unauthorized");
    }
}

package com.github.korenevaS.queue.error;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String user_already_exists) {
        super("User already exists");
    }
}

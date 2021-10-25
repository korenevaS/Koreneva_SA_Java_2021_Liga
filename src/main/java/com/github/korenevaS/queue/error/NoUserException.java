package com.github.korenevaS.queue.error;

public class NoUserException extends RuntimeException {
    public NoUserException() {
        super("No user found");
    }
}

package com.github.korenevaS.lesson5.exception;

public class NoSuchBookExistsException extends Exception {
    private final Integer id;

    public NoSuchBookExistsException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

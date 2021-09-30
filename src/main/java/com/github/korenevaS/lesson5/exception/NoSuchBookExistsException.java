package com.github.korenevaS.lesson5.exception;

import com.github.korenevaS.lesson5.model.Book;

public class NoSuchBookExistsException extends Exception {
    private final Integer id;

    public NoSuchBookExistsException(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

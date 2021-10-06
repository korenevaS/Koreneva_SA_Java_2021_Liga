package com.github.korenevaS.lesson5.exception;

import static java.lang.String.format;

public class NoSuchBookExistsException extends Exception {
    private final Integer id;

    public NoSuchBookExistsException(Integer id) {
        super(format("Книга с данным индентификатором не найдена: + '%s'", id.toString()));
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

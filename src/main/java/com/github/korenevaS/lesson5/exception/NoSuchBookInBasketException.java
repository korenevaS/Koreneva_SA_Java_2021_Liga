package com.github.korenevaS.lesson5.exception;

import com.github.korenevaS.lesson5.model.Book;

public class NoSuchBookInBasketException extends Exception {
    private final Book book;

    public NoSuchBookInBasketException(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}

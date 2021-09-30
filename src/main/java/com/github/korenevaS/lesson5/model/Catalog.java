package com.github.korenevaS.lesson5.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog {
    private final Map<Integer, Book> listOfBooks = new HashMap<>();

    public Catalog(List<Book> books) {
        if (books != null) {
            for (Book book : books) {
                listOfBooks.put(listOfBooks.size() + 1, book);
            }
        }
    }

    public Map<Integer, Book> getListBooks() {
        return Collections.unmodifiableMap(listOfBooks);
    }

    public boolean isBookExist(Integer id) {
        return listOfBooks.containsKey(id);
    }

    public Book findByID(Integer id) {
        return listOfBooks.get(id);
    }
}
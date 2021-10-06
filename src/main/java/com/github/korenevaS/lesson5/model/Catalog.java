package com.github.korenevaS.lesson5.model;

import com.github.korenevaS.lesson5.exception.NoSuchBookExistsException;

import java.util.*;

public class Catalog {
    private final LinkedHashMap<Integer, Book> mapOfBooks = new LinkedHashMap<>();

    public Catalog(List<Book> books) {
        if (books != null) {
            for (Book book : books) {
                mapOfBooks.put(mapOfBooks.size() + 1, book);
            }
        }
    }

    public Map<Integer, Book> getBooks() {
        return Collections.unmodifiableMap(mapOfBooks);
    }

    public boolean isBookExist(Integer id) {
        return mapOfBooks.containsKey(id);
    }

    public Book findByID(Integer id) throws NoSuchBookExistsException {
        return Optional.ofNullable(id)
                .map(mapOfBooks::get)
                .orElseThrow(() -> new NoSuchBookExistsException(id));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return Objects.equals(mapOfBooks, catalog.mapOfBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapOfBooks);
    }
}
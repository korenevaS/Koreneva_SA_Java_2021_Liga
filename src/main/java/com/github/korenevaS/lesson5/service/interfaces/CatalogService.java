package com.github.korenevaS.lesson5.service.interfaces;

import com.github.korenevaS.lesson5.model.Book;

import java.util.Map;

public interface CatalogService {
    public boolean checkBookDoesNotExistInCatalog(Integer id);

    public Map<Integer, Book> getMapBooks();

    public Book findBookById(Integer id);
}

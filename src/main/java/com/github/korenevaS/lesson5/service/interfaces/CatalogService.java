package com.github.korenevaS.lesson5.service.interfaces;

import com.github.korenevaS.lesson5.exception.NoSuchBookExistsException;
import com.github.korenevaS.lesson5.model.Book;

import java.util.Map;

public interface CatalogService {
    boolean checkBookDoesNotExistInCatalog(Integer id);

    Map<Integer, Book> getMapBooks();

    Book findBookById(Integer id) throws NoSuchBookExistsException;
}

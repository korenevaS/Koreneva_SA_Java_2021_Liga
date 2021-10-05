package com.github.korenevaS.lesson5.service;

import com.github.korenevaS.lesson5.model.Book;
import com.github.korenevaS.lesson5.model.Catalog;

import java.util.Arrays;
import java.util.Map;

public class CatalogService implements Service {
    private final Catalog catalog;

    public CatalogService(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean checkBookDoesNotExistInCatalog(Integer id) {
        return !catalog.isBookExist(id);
    }

    public Map<Integer, Book> getMapBooks() {
        return catalog.getListBooks();
    }

    public Book findBookById(Integer id) {
        return catalog.findByID(id);
    }

}

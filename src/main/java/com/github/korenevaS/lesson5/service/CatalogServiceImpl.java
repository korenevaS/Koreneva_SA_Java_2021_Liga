package com.github.korenevaS.lesson5.service;

import com.github.korenevaS.lesson5.model.Book;
import com.github.korenevaS.lesson5.model.Catalog;
import com.github.korenevaS.lesson5.service.interfaces.CatalogService;

import java.util.Map;

public class CatalogServiceImpl implements CatalogService {
    private final Catalog catalog;

    public CatalogServiceImpl(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean checkBookDoesNotExistInCatalog(Integer id) {
        return !catalog.isBookExist(id);
    }

    public Map<Integer, Book> getMapBooks() {
        return catalog.getBooks();
    }

    public Book findBookById(Integer id) {
        return catalog.findByID(id);
    }

}

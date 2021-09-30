package com.github.korenevaS.lesson5.service;

import com.github.korenevaS.lesson5.model.Book;
import com.github.korenevaS.lesson5.model.Catalog;

import java.util.Arrays;
import java.util.Map;

public class CatalogService {
    private static final Catalog catalog = new Catalog(Arrays.asList(new Book("Дуглас Адамс: Автостопом по галактике", 552),
            new Book("Алексей Поляринов: Риф", 488),
            new Book("Стивен Кинг: Дьюма-Ки", 507),
            new Book("Теодор Драйзер: Гений", 594),
            new Book("Джон Уиндем: Зов пространства", 470)));

    public static boolean checkBookDoesNotExistInCatalog(Integer id) {
        return !catalog.isBookExist(id);
    }

    public static Map<Integer, Book> getListBooks() {
        return catalog.getListBooks();
    }

    public static Book findBookById(Integer id) {
        return catalog.findByID(id);
    }
}

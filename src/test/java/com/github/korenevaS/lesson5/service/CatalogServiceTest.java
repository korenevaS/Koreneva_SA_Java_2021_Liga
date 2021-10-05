package com.github.korenevaS.lesson5.service;

import com.github.korenevaS.lesson5.model.Book;
import com.github.korenevaS.lesson5.model.Catalog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CatalogServiceTest {
    private final Catalog catalog = new Catalog(Arrays.asList(new Book("Дуглас Адамс: Автостопом по галактике", 552),
            new Book("Алексей Поляринов: Риф", 488),
            new Book("Стивен Кинг: Дьюма-Ки", 507),
            new Book("Теодор Драйзер: Гений", 594),
            new Book("Джон Уиндем: Зов пространства", 470)));

    private final CatalogService catalogService = new CatalogService(catalog);
    private final BasketService basketService = new BasketService(catalogService);

    Integer wrongId = 7;
    Integer id = 1;


    @DisplayName("Correct check of a book that does not exist in the directory.")
    @Test
    void correctCheckBookDoesNotExistInCatalog () {
        assertTrue(catalogService.checkBookDoesNotExistInCatalog(wrongId));

    }

    @DisplayName("Correct check of the book existing in the catalog.")
    @Test
    void correctCheckBookDoesExistInCatalog () {
        assertFalse(catalogService.checkBookDoesNotExistInCatalog(id));
    }

    @DisplayName("Correct get map of the books.")
    @Test
    void correctGetMapBooks() {
        System.out.println(catalog.getListBooks());
        System.out.println(catalogService.getMapBooks());
        assertEquals(catalog.getListBooks(), catalogService.getMapBooks());
    }

    @DisplayName("Correct find book by id.")
    @Test
    void correctFindById() {
        catalog.findByID(id);
        catalogService.findBookById(id);
    }

    @DisplayName("Correct find book by wrong id.")
    @Test
    void correctFindByWrongId() {
        catalog.findByID(wrongId);
        catalogService.findBookById(wrongId);
    }
}
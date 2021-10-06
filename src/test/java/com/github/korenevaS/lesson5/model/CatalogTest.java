package com.github.korenevaS.lesson5.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {

    Book book1 = new Book("Дуглас Адамс: Автостопом по галактике", 552);

    Catalog catalog = new Catalog(Arrays.asList(book1,
            new Book("Алексей Поляринов: Риф", 488),
            new Book("Стивен Кинг: Дьюма-Ки", 507),
            new Book("Теодор Драйзер: Гений", 594),
            new Book("Джон Уиндем: Зов пространства", 470))
    );
    Integer id = 1;
    Integer wrongId = 10;


    @DisplayName("Checking if the book exists in the catalog.")
    @Test
    void correctIsBookExist() {
        assertTrue(catalog.isBookExist(id));
    }

    @DisplayName("Checking if the book exists in the catalog.")
    @Test
    void correctIsBookDoesNotExist() {
        assertFalse(catalog.isBookExist(wrongId));
    }

    @DisplayName("Finding the book by the correct id.")
    @Test
    void correctFindByID() {
        assertEquals(book1, catalog.findByID(id));
    }

    @DisplayName("Finding the book by the wrong id.")
    @Test
    void correctFindByWrongID() {
        assertNull(catalog.findByID(wrongId));
    }
}
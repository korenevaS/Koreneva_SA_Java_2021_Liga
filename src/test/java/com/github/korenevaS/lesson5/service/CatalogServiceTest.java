package com.github.korenevaS.lesson5.service;

import com.github.korenevaS.lesson5.exception.NoSuchBookExistsException;
import com.github.korenevaS.lesson5.model.Book;
import com.github.korenevaS.lesson5.model.Catalog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CatalogServiceTest {
    Catalog catalogMock = Mockito.mock(Catalog.class);
    private final CatalogServiceImpl catalogService = new CatalogServiceImpl(catalogMock);

    Integer wrongId = 7;
    Integer id = 1;

    @BeforeEach
    void initMock() throws NoSuchBookExistsException {
        when(catalogMock.isBookExist(id)).thenReturn(true);
        when(catalogMock.findByID(id)).thenReturn(new Book("for-example1", 500));
        when(catalogMock.findByID(wrongId)).thenReturn(null);
    }

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

    @DisplayName("Correct find book by id.")
    @Test
    void correctFindById() throws NoSuchBookExistsException {
        assertEquals(catalogMock.findByID(id), catalogService.findBookById(id));
    }

    @DisplayName("Correct find book by wrong id.")
    @Test
    void correctFindByWrongId() throws NoSuchBookExistsException {
        assertEquals(catalogMock.findByID(wrongId), catalogService.findBookById(wrongId));
    }
}
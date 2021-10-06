package com.github.korenevaS.lesson5.service;

import com.github.korenevaS.lesson5.exception.NoSuchBookExistsException;
import com.github.korenevaS.lesson5.exception.NoSuchBookInBasketException;
import com.github.korenevaS.lesson5.model.Basket;
import com.github.korenevaS.lesson5.model.Book;
import com.github.korenevaS.lesson5.service.interfaces.CatalogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BasketServiceTest {
    CatalogService catalogServiceMock = Mockito.mock(CatalogService.class);
    BasketService basketService = new BasketService(catalogServiceMock);

    Integer id1 = 1;
    Integer id2 = 2;
    Integer wrongId = 8;

    @BeforeEach
    void initMock() {
        when(catalogServiceMock.findBookById(id1)).thenReturn(new Book("for-example1", 500));
        when(catalogServiceMock.findBookById(id2)).thenReturn(new Book("for-example2", 500));
        when(catalogServiceMock.checkBookDoesNotExistInCatalog(wrongId)).thenReturn(true);
    }

    @DisplayName("Correctly adding a book to the cart.")
    @Test
    void correctAddPurchaseToBasket () throws NoSuchBookExistsException {
        Basket basketForComparison = new Basket();
        assertTrue(basketForComparison.isBasketEmpty());
        assertFalse(basketForComparison.isPurchaseContainsBook(catalogServiceMock.findBookById(id1)));
        basketService.addPurchaseToBasket(basketForComparison, id1);
        assertFalse(basketForComparison.isBasketEmpty());
        assertTrue(basketForComparison.isPurchaseContainsBook(catalogServiceMock.findBookById(id1)));
    }

    @DisplayName("Correctly adding two identical books to the basket.")
    @Test
    void correctAddTwoIdenticalPurchaseToBasket() throws NoSuchBookExistsException {
        Basket basketForComparison = new Basket();
        basketService.addPurchaseToBasket(basketForComparison, id1);
        basketService.addPurchaseToBasket(basketForComparison, id1);
        assertFalse(basketForComparison.isBasketEmpty());
        assertTrue(basketForComparison.isPurchaseContainsBook(catalogServiceMock.findBookById(id1)));
        assertEquals(2, basketForComparison.getBookQuantity(catalogServiceMock.findBookById(id1)));
    }

   @DisplayName("Correctly adding two different books to basket.")
    @Test
    void correctAddTwoDifferentPurchaseToBasket() throws NoSuchBookExistsException{
       Basket basketForComparison = new Basket();
       basketService.addPurchaseToBasket(basketForComparison, id1);
       basketService.addPurchaseToBasket(basketForComparison, id2);
       assertEquals(1, basketForComparison.getBookQuantity(catalogServiceMock.findBookById(id1)));
       assertEquals(1, basketForComparison.getBookQuantity(catalogServiceMock.findBookById(id2)));
    }

    @DisplayName("Correctly adding a book with the wrong id.")
    @Test
    void correctAddPurchaseWithWrongIToBasket() {
        Basket basketForComparison = new Basket();
        assertThrows(NoSuchBookExistsException.class, () -> basketService.addPurchaseToBasket(basketForComparison, wrongId));
    }

    @DisplayName("Correctly deleting one book.")
    @Test
    void correctRemovePurchaseFromBasket() throws NoSuchBookExistsException, NoSuchBookInBasketException {
        Basket basketForComparison = new Basket();
        basketService.addPurchaseToBasket(basketForComparison, id1);
        assertFalse(basketForComparison.isBasketEmpty());
        assertEquals(1, basketForComparison.getBookQuantity(catalogServiceMock.findBookById(id1)));
        basketService.removePurchaseFromBasket(basketForComparison,id1);
        assertTrue(basketForComparison.isBasketEmpty());

    }


    @DisplayName("Correctly deleting one of two identical books.")
    @Test
    void correctRemoveOneOfTwoPurchaseFromBasket() throws NoSuchBookInBasketException, NoSuchBookExistsException {
        Basket basketForComparison = new Basket();
        basketService.addPurchaseToBasket(basketForComparison, id1);
        basketService.addPurchaseToBasket(basketForComparison, id1);
        assertEquals(2, basketForComparison.getBookQuantity(catalogServiceMock.findBookById(id1)));
        basketService.removePurchaseFromBasket(basketForComparison,id1);
        assertEquals(1, basketForComparison.getBookQuantity(catalogServiceMock.findBookById(id1)));
    }

    @DisplayName("Correctly deleting a book with the wrong id.")
    @Test
    void correctRemovePurchaseWithWrongIToBasket(){
        Basket basketForComparison = new Basket();
        assertThrows(NoSuchBookExistsException.class, () -> basketService.removePurchaseFromBasket(basketForComparison, wrongId));
    }

    @DisplayName("Correct printout of an empty basket.")
    @Test
    void correctGetEmptyBasketDescription() {
        Basket basketForComparison = new Basket();
        String strForComparison = "Корзина пуста.";
        assertEquals(strForComparison, basketService.getBasketDescription(basketForComparison));
    }


    @DisplayName("Correct printout of the basket with one book.")
    @Test
    void correctGetBasketWithOneBookDescription() throws NoSuchBookExistsException {
        Basket basketForComparison = new Basket();
        String strForComparison = "Корзина:\n" +
                "for-example1\n" +
                ". . . . . . . .1\n" +
                "Общая сумма покупки: 500";
        basketService.addPurchaseToBasket(basketForComparison, id1);
        assertEquals(strForComparison, basketService.getBasketDescription(basketForComparison));
    }

    @DisplayName("Correct printout of a basket with two identical books.")
    @Test
    void correctGetBasketWithTwoBookDescription() throws NoSuchBookExistsException {
        Basket basketForComparison = new Basket();
        String strForComparison = "Корзина:\n" +
                "for-example1\n" +
                ". . . . . . . .2\n" +
                "Общая сумма покупки: 1000";
        basketService.addPurchaseToBasket(basketForComparison, id1);
        basketService.addPurchaseToBasket(basketForComparison, id1);
        assertEquals(strForComparison, basketService.getBasketDescription(basketForComparison));
    }

    @DisplayName("Correct printout of a basket with two different books.")
    @Test
    void correctGetBasketWithTwoDifferentBookDescription() throws NoSuchBookExistsException {
        Basket basketForComparison = new Basket();
        String strForComparison = "Корзина:\n" +
                "for-example2\n. . . . . . . .1\n" +
                "for-example1\n. . . . . . . .1\n" +
                "Общая сумма покупки: 1000";
        basketService.addPurchaseToBasket(basketForComparison, id2);
        basketService.addPurchaseToBasket(basketForComparison, id1);
        assertEquals(strForComparison, basketService.getBasketDescription(basketForComparison));
    }
}
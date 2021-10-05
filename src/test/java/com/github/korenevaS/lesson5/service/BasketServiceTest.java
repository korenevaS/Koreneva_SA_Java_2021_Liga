package com.github.korenevaS.lesson5.service;

import com.github.korenevaS.lesson5.exception.NoSuchBookExistsException;
import com.github.korenevaS.lesson5.exception.NoSuchBookInBasketException;
import com.github.korenevaS.lesson5.model.Basket;
import com.github.korenevaS.lesson5.model.Book;
import com.github.korenevaS.lesson5.model.Catalog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class BasketServiceTest {
    private final CatalogService catalogService = new CatalogService(
            new Catalog(Arrays.asList(new Book("Дуглас Адамс: Автостопом по галактике", 552),
                    new Book("Алексей Поляринов: Риф", 488),
                    new Book("Стивен Кинг: Дьюма-Ки", 507),
                    new Book("Теодор Драйзер: Гений", 594),
                    new Book("Джон Уиндем: Зов пространства", 470)))
    );
    private final BasketService basketService = new BasketService(catalogService);

    Basket basket = new Basket();
    Integer id1 = 1;
    Integer id2 = 2;
    Integer wrongId = 8;
    Basket basketForComparison = new Basket();

    @DisplayName("Correctly adding a book to the cart.")
    @Test
    void correctAddPurchaseToBasket () throws NoSuchBookExistsException {
        basketForComparison.putPurchase(catalogService.findBookById(id1), 1);
        basketService.addPurchaseToBasket(basket, id1);
        assertEquals(basketForComparison, basket);
    }

    @DisplayName("Correctly adding two identical books to the basket.")
    @Test
    void correctAddTwoIdenticalPurchaseToBasket() throws NoSuchBookExistsException {
        basketForComparison.putPurchase(catalogService.findBookById(id1), 2);
        basketService.addPurchaseToBasket(basket, id1);
        basketService.addPurchaseToBasket(basket, id1);
        assertEquals(basketForComparison, basket);
    }

    @DisplayName("Correctly adding two different books to basket.")
    @Test 
    void correctAddTwoDifferentPurchaseToBasket() throws NoSuchBookExistsException{
        basketForComparison.putPurchase(catalogService.findBookById(id1), 1);
        basketForComparison.putPurchase(catalogService.findBookById(id2), 1);
        basketService.addPurchaseToBasket(basket, id1);
        basketService.addPurchaseToBasket(basket, id2);
        assertEquals(basketForComparison, basket);
    }

    @DisplayName("Correctly adding a book with the wrong id.")
    @Test
    void correctAddPurchaseWithWrongIToBasket() {
        assertThrows(NoSuchBookExistsException.class, () -> basketService.addPurchaseToBasket(basket, wrongId));
    }

    @DisplayName("Correctly deleting one book.")
    @Test
    void correctRemovePurchaseFromBasket() throws NoSuchBookInBasketException, NoSuchBookExistsException {
        basketService.addPurchaseToBasket(basketForComparison,id1);
        basketService.addPurchaseToBasket(basket, id1);
        basketForComparison.removePurchase(catalogService.findBookById(id1));
        basketService.removePurchaseFromBasket(basket, id1);
        assertEquals(basketForComparison, basket);
    }

    @DisplayName("Correctly deleting one of two identical books.")
    @Test
    void correctRemoveOneOfTwoPurchaseFromBasket() throws NoSuchBookInBasketException, NoSuchBookExistsException {
        basketService.addPurchaseToBasket(basketForComparison,id1);
        basketService.addPurchaseToBasket(basket, id1);
        basketService.addPurchaseToBasket(basket, id1);
        basketService.removePurchaseFromBasket(basket, id1);
        assertEquals(basketForComparison, basket);
    }

    @DisplayName("Correctly deleting a book with the wrong id.")
    @Test
    void correctRemovePurchaseWithWrongIToBasket() throws NoSuchBookExistsException {
        basketService.addPurchaseToBasket(basketForComparison,id1);
        basketService.addPurchaseToBasket(basket, id1);
        assertThrows(NoSuchBookExistsException.class, () -> basketService.addPurchaseToBasket(basket, wrongId));
    }

    @DisplayName("Correct printout of an empty basket.")
    @Test
    void correctGetEmptyBasketDescription() {
        String strForComparison = "Корзина пуста.";
        assertEquals(strForComparison, basketService.getBasketDescription(basket));
    }

    @DisplayName("Correct printout of the basket with one book.")
    @Test
    void correctGetBasketWithOneBookDescription() throws NoSuchBookExistsException {
        String strForComparison = "Корзина:\n" +
                "Дуглас Адамс: Автостопом по галактике\n" +
                ". . . . . . . .1\n" +
                "Общая сумма покупки: 552";
        basketService.addPurchaseToBasket(basket, id1);
        assertEquals(strForComparison, basketService.getBasketDescription(basket));
    }

    @DisplayName("Correct printout of a basket with two identical books.")
    @Test
    void correctGetBasketWithTwoBookDescription() throws NoSuchBookExistsException {
        String strForComparison = "Корзина:\n" +
                "Дуглас Адамс: Автостопом по галактике\n" +
                ". . . . . . . .2\n" +
                "Общая сумма покупки: 1104";
        basketService.addPurchaseToBasket(basket, id1);
        basketService.addPurchaseToBasket(basket, id1);
        assertEquals(strForComparison, basketService.getBasketDescription(basket));
    }
    @DisplayName("Correct printout of a basket with two different books.")
    @Test
    void correctGetBasketWithTwoDifferentBookDescription() throws NoSuchBookExistsException {
        String strForComparison = "Корзина:\n" +
                "Алексей Поляринов: Риф\n. . . . . . . .1\n" +
                "Дуглас Адамс: Автостопом по галактике\n. . . . . . . .1\n" +
                "Общая сумма покупки: 1040";
        basketService.addPurchaseToBasket(basket, id2);
        basketService.addPurchaseToBasket(basket, id1);
        assertEquals(strForComparison, basketService.getBasketDescription(basket));
    }
}
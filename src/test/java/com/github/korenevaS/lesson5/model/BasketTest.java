package com.github.korenevaS.lesson5.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {
    Basket basket = new Basket();
    Integer id = 1;
    Book bookForComparison = new Book("for-example1", id);

    @DisplayName("Correct check of adding purchase.")
    @Test
    void correctPutPurchase() {
        assertTrue(basket.isBasketEmpty());
        basket.putPurchase(bookForComparison, 1);
        assertFalse(basket.isBasketEmpty());
        assertTrue(basket.isPurchaseContainsBook(bookForComparison));
    }

    @DisplayName("Correct check if the purchase contains a book.")
    @Test
    void correctIsPurchaseContainsBook() {
        basket.putPurchase(bookForComparison, 1);
        assertTrue(basket.isPurchaseContainsBook(bookForComparison));
    }

    @DisplayName("Correct check if the purchase does not contains a book.")
    @Test
    void correctIsPurchaseDoesNotContainsBook() {
        assertFalse(basket.isPurchaseContainsBook(bookForComparison));
    }

    @DisplayName("Correct check of remove purchase.")
    @Test
    void correctRemovePurchase() {
        assertTrue(basket.isBasketEmpty());
        basket.putPurchase(bookForComparison, 1);
        assertFalse(basket.isBasketEmpty());
        assertTrue(basket.isPurchaseContainsBook(bookForComparison));
        basket.removePurchase(bookForComparison);
        assertTrue(basket.isBasketEmpty());
    }

    @DisplayName("Checking empty basket correctly.")
    @Test
    void correctIsBasketEmpty() {
        assertTrue(basket.isBasketEmpty());
    }

    @DisplayName("Checking empty basket correctly.")
    @Test
    void correctIsBasketDoesNotEmpty() {
        basket.putPurchase(bookForComparison, 1);
        assertFalse(basket.isBasketEmpty());
    }

}
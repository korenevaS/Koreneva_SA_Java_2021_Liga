package com.github.korenevaS.lesson5.model;

import java.util.*;

public class Basket {
    private final Map<Book, Integer> purchase = new LinkedHashMap<>();

    public void putPurchase(Book goods, int number) {
        purchase.put(goods, number);
    }

    public int getBookQuantity(Book goods) {
        return purchase.get(goods);
    }

    public boolean isPurchaseContainsBook(Book goods) {
        return purchase.containsKey(goods);
    }

    public void removePurchase(Book goods) {
        purchase.remove(goods);
    }

    public boolean isBasketEmpty() {
        return purchase.isEmpty();
    }

    public Set<Map.Entry<Book, Integer>> getEntrySet() {
        return purchase.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return Objects.equals(purchase, basket.purchase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchase);
    }


}

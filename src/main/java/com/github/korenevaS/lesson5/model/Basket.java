package com.github.korenevaS.lesson5.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Basket {
    private final Map<Book, Integer> purchase = new HashMap<>();

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
}

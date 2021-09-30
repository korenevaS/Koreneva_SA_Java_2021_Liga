package com.github.korenevaS.lesson5.service;

import com.github.korenevaS.lesson5.exception.NoSuchBookInBasketException;
import com.github.korenevaS.lesson5.exception.NoSuchBookExistsException;
import com.github.korenevaS.lesson5.model.Basket;
import com.github.korenevaS.lesson5.model.Book;

import java.util.Map;

public class BasketService implements Service {

    public void addPurchaseToBasket(Basket basket, Integer id) throws NoSuchBookExistsException {
        if (CatalogService.checkBookDoesNotExistInCatalog(id)) {
            throw new NoSuchBookExistsException(id);
        }
        Book goods = CatalogService.findBookById(id);
        if (basket.isPurchaseContainsBook(goods)) {
            int number = basket.getBookQuantity(goods);
            basket.putPurchase(goods, number + 1);
        } else {
            basket.putPurchase(goods, 1);
        }
    }

    public void removePurchaseFromBasket(Basket basket, Integer id) throws NoSuchBookInBasketException, NoSuchBookExistsException {
        if (CatalogService.checkBookDoesNotExistInCatalog(id)) {
            throw new NoSuchBookExistsException(id);
        }
        Book goods = CatalogService.findBookById(id);
        if (basket.isPurchaseContainsBook(goods)) {
            if (basket.getBookQuantity(goods) == 1) {
                basket.removePurchase(goods);
            } else {
                int number = basket.getBookQuantity(goods);
                basket.putPurchase(goods, number - 1);
            }
        } else {
            throw new NoSuchBookInBasketException(goods);
        }
    }

    public String getBasketDescription(Basket basket) {
        StringBuilder str = new StringBuilder();
        if (basket.isBasketEmpty()) {
            str.append("Корзина пуста.");
        } else {
            int cost = 0;
            str.append("Корзина:\n");
            for (Map.Entry<Book, Integer> entry : basket.getEntrySet()) {
                Book book = entry.getKey();
                Integer id = entry.getValue();
                str.append(book.getName());
                str.append("\n");
                str.append(". . . . . . . .").append(id).append("\n");
                cost = cost + id * book.getCost();
            }
            str.append("Общая сумма покупки: ").append(cost);
        }
        return str.toString();
    }
}

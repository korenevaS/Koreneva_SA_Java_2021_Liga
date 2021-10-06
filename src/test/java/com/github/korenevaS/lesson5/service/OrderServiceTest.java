package com.github.korenevaS.lesson5.service;

import com.github.korenevaS.lesson5.service.interfaces.CatalogService;
import com.github.korenevaS.lesson5.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    OrderService orderService = new OrderService();
    CatalogService catalogServiceMock = Mockito.mock(CatalogService.class);
    BasketService basketServiceMock = Mockito.mock(BasketService.class);
    Basket basketMock = Mockito.mock(Basket.class);
    Basket emptyBasket = Mockito.mock(Basket.class);
    Basket filledBasket = Mockito.mock(Basket.class);
    Customer customer = Mockito.mock(Customer.class);

    @BeforeEach
    void initMock() {
        when(emptyBasket.isBasketEmpty()).thenReturn(true);
        when(filledBasket.isBasketEmpty()).thenReturn(false);
        when(basketServiceMock.getBasketDescription(emptyBasket)).thenReturn("Корзина пуста.");
        when(customer.getName()).thenReturn("Эмма");
        when(customer.getAddress()).thenReturn("Дом");
    }

    @DisplayName("Refused order with empty shopping basket.")
    @Test
    void correctGetEmptyOrderDescription () {
        Order emptyOrder = new Order(customer, emptyBasket);
        String result = "Корзина пуста. Невозможно оформить заказ.";
        assertEquals(result, orderService.getOrderDescription(emptyOrder, basketServiceMock.getBasketDescription(emptyBasket)));
    }

    @DisplayName("Printing an order with a filled basket.")
    @Test
    void correctGetOrderDescription() {
        Order filledOrder = new Order(customer, filledBasket);
        String basketDescription = "Корзина:\nfor-example1\n. . . . . . . .1\nОбщая сумма покупки: 500";
        String result = filledOrder.getCustomer().getName() + ", ваш заказ будет передан курьеру сразу после подтверждения заказа.\n" +
                basketDescription + "\nДоставка будет произведена по адресу:\n" + filledOrder.getCustomer().getAddress();
        assertEquals(result, orderService.getOrderDescription(filledOrder, basketDescription));
    }
}
package com.github.korenevaS.lesson5.service;

import com.github.korenevaS.lesson5.model.Order;

public class OrderService {

    public String getOrderDescription(Order order, String basketDescription) {
        if (!order.getBasket().isBasketEmpty()) {
            return order.getCustomer().getName() + ", ваш заказ будет передан курьеру сразу после подтверждения заказа.\n" +
                    basketDescription + "\nДоставка будет произведена по адресу:\n" + order.getCustomer().getAddress();
        } else {
            return "Корзина пуста. Невозможно оформить заказ.";
        }
    }
}

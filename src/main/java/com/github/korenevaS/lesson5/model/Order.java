package com.github.korenevaS.lesson5.model;

public class Order {
    private final Customer customer;
    private final Basket basket;

    public Customer getCustomer() {
        return customer;
    }

    public Basket getBasket() {
        return basket;
    }

    public Order(Customer customer, Basket basket) {
        this.customer = customer;
        this.basket = basket;
    }
}

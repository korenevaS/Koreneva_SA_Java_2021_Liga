package com.github.korenevaS.lesson5.model;

public class Book {
    private final String name;
    private final int cost;

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Book(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "" + name + ", cost: " + cost + "";
    }
}

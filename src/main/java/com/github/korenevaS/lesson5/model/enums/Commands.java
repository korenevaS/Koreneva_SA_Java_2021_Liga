package com.github.korenevaS.lesson5.model.enums;

public enum Commands {
    CATALOG("catalog"),
    BASKET("basket"),
    ORDER("order"),
    CONFIRM("confirm"),
    ADD("add"),
    REMOVE("remove");
    private final String code;

    Commands(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

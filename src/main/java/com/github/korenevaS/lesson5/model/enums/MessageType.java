package com.github.korenevaS.lesson5.model.enums;

public enum MessageType {
    ENTER_NAME("Пожалуйста, введите свое имя, содержащее только буквы:"),
    WRONG_NAME("Недопустимые символы. Пожалуйста, введите корректное имя:"),
    ENTER_PHONE("Пожалуйста, введите свой номер телефона в формате +79999999999:"),
    WRONG_PHONE("Недопустимые символы. Пожалуйста, введите корректный номер телефона:"),
    ENTER_ADDRESS("Пожалуйста, введите адрес:"),
    WRONG_COMMAND("Неверная команда."),
    WELCOME(", добро пожаловать в BookShop!"),
    CONFIRM_ORDER("Для подтверждения заказа введите "),
    ORDER_SHIPPED("Заказ подтвержден и передан курьеру. "),
    ORDER_FAILED("Заказ не подтвержден. Для изменения заказа продолжите вводить известные команды."),
    REMOVE_SUCCESS("Товар успешно удален из корзины."),
    REMOVE_ERROR("Товара %s нет в корзине."),
    ADD_SUCCESS("Товар успешно добавлен в корзину."),
    WRONG_ID("Введен несуществующий id товара.");


    private final String message;

    MessageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

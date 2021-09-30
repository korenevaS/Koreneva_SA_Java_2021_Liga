package com.github.korenevaS.lesson5.util;

import com.github.korenevaS.lesson5.model.enums.Commands;
import com.github.korenevaS.lesson5.model.enums.MessageType;

public class ConsoleUtil {
    public static void printMessage(MessageType messageType) {
        System.out.println(messageType.getMessage());
    }

    public static void printWelcomeMessage(String name) {
        System.out.println(name + MessageType.WELCOME.getMessage());
    }

    public static void printConfirmOrder(String code) {
        System.out.println(MessageType.CONFIRM_ORDER.getMessage() + "\"" + code + "\"");
    }

    public static void printRemoveError(String bookName) {
        System.out.printf((MessageType.REMOVE_ERROR.getMessage()) + "%n", bookName);
    }

    public static void printHint() {
        System.out.println("Если вы хотите...");
        System.out.println("\t - увидеть каталог товаров, введите \"" + Commands.CATALOG.getCode() + "\".");
        System.out.println("\t - добавить книгу в корзину, введите \"" + Commands.ADD.getCode() + " n\", " +
                "где n - id нужной книги.");
        System.out.println("\t - удалить книгу из корзину, введите \"" + Commands.REMOVE.getCode() + " n\", " +
                "где n - id нужной книги.");
        System.out.println("\t - увидеть содержимое корзины, введите \"" + Commands.BASKET.getCode() + "\"");
        System.out.println("\t - перейти к оформлению заказа, введите \"" + Commands.ORDER.getCode() + "\".");
    }
}

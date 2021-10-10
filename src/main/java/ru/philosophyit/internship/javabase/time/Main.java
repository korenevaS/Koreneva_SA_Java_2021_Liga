package ru.philosophyit.internship.javabase.time;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Main {
    public static void main(String ...args) {
        System.out.println(DateTimeFormatter.ISO_INSTANT.format(Calendar.getInstance().toInstant()));

        // Отобразите календарь текущего месяца в консоли
        // например:
        // пн вт ср чт пт сб вс
        // 30 31 1  2  3  4  5
        // и т.д.
    }
}

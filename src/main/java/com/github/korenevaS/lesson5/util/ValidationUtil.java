package com.github.korenevaS.lesson5.util;

public class ValidationUtil {
    public static Boolean isNameCorrect(String name) {
        String regex = "^[a-zA-Zа-яА-Я]+$";
        return name.matches(regex);
    }

    public static Boolean isPhoneCorrect(String phone) {
        String regex = "^\\+7\\d{10}$";
        return phone.matches(regex);
    }
}

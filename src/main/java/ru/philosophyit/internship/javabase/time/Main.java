package ru.philosophyit.internship.javabase.time;

import java.time.LocalDate;
import java.util.Locale;

public class Main {
    public static void main(String... args) {
        //System.out.println(DateTimeFormatter.ISO_INSTANT.format(Calendar.getInstance().toInstant()));
        // Отобразите календарь текущего месяца в консоли
        // например:
        // пн вт ср чт пт сб вс
        // 30 31 1  2  3  4  5
        // и т.д.
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_WHITE = "\u001B[37m";

        LocalDate currentDate = LocalDate.now();

        System.out.println(ANSI_RED + currentDate.getYear() + ", " + currentDate.getMonth().toString().toLowerCase(Locale.ROOT) + ANSI_RESET);
        LocalDate firstDayOfMonth = currentDate.minusDays(currentDate.getDayOfMonth() - 1);
        int startThisMonth = firstDayOfMonth.getDayOfWeek().getValue();

        int numberOfDaysInPreviousMonth = currentDate.minusMonths(1).getMonth().length(currentDate.isLeapYear());

        System.out.println("mo tu we th fr sa su");

        int numOfDay = 0;
        for (int i = numberOfDaysInPreviousMonth - startThisMonth + 2; numOfDay + 1 < startThisMonth; i++) {
            System.out.print(ANSI_WHITE);
            System.out.printf("%-3d", i);
            System.out.print(ANSI_RESET);
            numOfDay++;
        }

        for (int i = 1; i <= currentDate.getMonth().length(currentDate.isLeapYear()); i++) {
            if (numOfDay == 7) {
                System.out.print("\n");
                numOfDay = 0;
            }
            if (numOfDay == 5 || numOfDay == 6) {
                System.out.print(ANSI_RED);
                System.out.printf("%-3d", i);
                System.out.print(ANSI_RESET);
            } else {
                System.out.printf("%-3d", i);
            }
            numOfDay++;
        }
    }
}

package ru.philosophyit.internship.javabase.threads;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class Completable {
    // Почему в выводе нет "Hello world"?

    //Main отдает задачи потокам и тут же завершает работу. Так как остальные потоки - демоны,
    //они завершат свою работу сразу же, когда работу завершат основные потоки (не демоны), а такой
    //у нас только один - Main.
    //Если мы заставим поток Main ждать завершение остальных потоков, то увидим в выводе "Hello world".
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.println("first produced");
            return "Hello ";
        }).thenCompose(first -> CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            return first + "world";
        }))
        .thenApply(Function.identity())
        .thenAccept(System.out::println);
    }
}

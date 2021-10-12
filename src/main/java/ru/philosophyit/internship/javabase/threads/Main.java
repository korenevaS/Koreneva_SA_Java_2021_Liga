package ru.philosophyit.internship.javabase.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    /*1. Почему при вызове executorService.shutdown(); программа продолжает свое исполнение ?
        Метод shutdown не дает принимать новые задачи, но позволяет выполнить уже имеющиеся.

    2. Почему если убрать строчку 28 (executorService.shutdown()) программа не прекратит свое исполнение
    даже после завершения всех тасок в executorService ?
        newFixedThreadPool() не имеет тайминга на работу нитей, поэтому они будут работать, пока их кто-то не завершит.
        Main ждет, пока завершаться выделенные потоки, но они не могут этого сделать.

    3. Почему при работе тасок executorService в консоль в секунду попадает всего 4 сообщения, тогда как тасок
    в executorService - 16?
        При создании executorService было открыто 4 потока. Каждый из поток занят выполнением одной задачи, только
        после выполнения которой он переходит к выполнению следующей.*/

    public static void main(String[] args) {
        startSomeDaemon();

        int num = getThreadsCount();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < num; i++) {
            int captureId = i;
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
                System.err.println(String.format("Hello from %d callable", captureId));
            });
        }
        executorService.shutdown();
    }

    private static int getThreadsCount() {
        return 16;
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//            int num = Integer.valueOf(reader.readLine());
//            return num;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return 0;
    }

    private static void startSomeDaemon() {
        Thread thread = new Thread(() -> {
            int t = 0;
            while (true) {
                System.err.println(t++);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}

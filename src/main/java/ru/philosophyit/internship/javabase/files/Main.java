package ru.philosophyit.internship.javabase.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    static class PathPart {
        private final String name;
        private final List<PathPart> children = new ArrayList<>();

        PathPart(String name) {
            this.name = name;
        }
    }

    private static void depthTraversal(PathPart current, int depth) {
        System.out.println("\t".repeat(Math.max(depth - 1, 0)) + current.name);
        for (PathPart child : current.children) {
            depthTraversal(child, depth + 1);
        }
    }

    public static void main(String... args) throws IOException {
        System.out.println(Files.readString(Path.of("src/main/resources/hello.txt")));
        // Отобразите рекурсивно дерево директорий src/main/java/ru/philosophyit/internship/javabase
        // например 
        // src/main/java/ru/philosophyit/internship/javabase/files/Main.java
        // src/main/java/ru/philosophyit/internship/javabase/locks/DeadLock.java
        // src/main/java/ru/philosophyit/internship/javabase/locks/LiveLock.java
        // src/main/java/ru/philosophyit/internship/javabase/threads/Completable.java
        // и т.д.
        /// Более удачные оформления вывода в консоль приветствуются

        PathPart root = new PathPart("");
        Files.walk(Path.of("src/main/java/ru/philosophyit/internship/javabase")).forEach((path) -> {
            String[] split = path.toString().split("\\\\");
            PathPart current = root;

            for (String part : split) {
                boolean found = false;
                for (PathPart child : current.children) {
                    if (Objects.equals(child.name, part)) {
                        current = child;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    PathPart next = new PathPart(part);
                    current.children.add(next);
                    current = next;
                }
            }
        });
        depthTraversal(root, 0);
    }
}

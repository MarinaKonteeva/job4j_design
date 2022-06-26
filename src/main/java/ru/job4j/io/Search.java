package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        val(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static void val(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("неправильные аргументы");
        }
        File file = new File(args[0]);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("первый аргумент должен быть путь до папки поиска");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("второй аргумент должен быть расширение файла");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
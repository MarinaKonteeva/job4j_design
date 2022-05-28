package ru.job4j.io;

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
        try {
            Path start = Paths.get(args[0]);
        } catch (Exception ex) {
            throw new IllegalArgumentException("первый аргумент должен быть путь до папки поиска");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();

    }
}
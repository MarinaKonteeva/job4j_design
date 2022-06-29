package ru.job4j.io.test;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class FindFile {

    public static void val(String directory, String name, String type, String output) {

        File file = new File(directory);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(" -d должен быть путь до папки");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException(" -n нет названия файла или маски");
        }
        if (!type.equals("mask") && !type.equals("name")) {
            throw new IllegalArgumentException(" -t неверный аргумент");
        }
        if (output.length() == 0) {
            throw new IllegalArgumentException(" -o нет названия файла");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        ru.job4j.io.SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void savePath(List<Path> paths, String fileName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (var path : paths) {
                pw.println(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ru.job4j.io.ArgsName argsName = ArgsName.of(args);
        val(argsName.get("d"), argsName.get("n"), argsName.get("t"), argsName.get("o"));
        Path start = Paths.get(argsName.get("d"));

        Predicate<Path> condition = null;

        if (argsName.get("t").equals("name")) {
            condition = p -> p.toFile().getName().equals(argsName.get("n"));
        }
        if (argsName.get("t").equals("mask")) {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + argsName.get("n"));
            condition = p -> matcher.matches(p.getFileName());
        }
        var paths = search(start, condition);
        savePath(paths, argsName.get("o"));
    }
}

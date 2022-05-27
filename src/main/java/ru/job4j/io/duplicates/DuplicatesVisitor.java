package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    HashMap<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        File file1 = new File(file.toString());
        FileProperty fileProperty = new FileProperty(file1.length(), file1.getName());
        files.putIfAbsent(fileProperty, new ArrayList<>());
        files.get(fileProperty).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void printDublicate() {
        for (var paths : files.values()) {
            if (paths.size() > 1) {
                for (var path : paths) {
                    System.out.println(path);
                }
            }
        }
    }
}
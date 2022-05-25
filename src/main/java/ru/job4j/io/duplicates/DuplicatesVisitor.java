package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    HashSet<FileProperty> files = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        File file1 = new File(file.toString());
        FileProperty fileProperty = new FileProperty(file1.length(), file1.getName());
        if (!files.contains(fileProperty)) {
            files.add(fileProperty);
        } else {
            System.out.println(fileProperty.getName());
        }
        return super.visitFile(file, attrs);
    }
}
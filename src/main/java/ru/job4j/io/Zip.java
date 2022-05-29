package ru.job4j.io;

import ru.job4j.io.duplicates.DuplicatesVisitor;
import ru.job4j.io.duplicates.FileProperty;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (var source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<File> collect(Path dir, String excl) throws IOException {
        var walker = new Collector(excl);
        Files.walkFileTree(dir, walker);
        return walker.getFiles();
    }

    private class Collector extends SimpleFileVisitor<Path> {

        List<File> files = new ArrayList<>();
        String excl;

        public Collector(String excl) {
            this.excl = excl;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (!file.toString().endsWith(excl)) {
                files.add(new File(file.toString()));
            }
            return super.visitFile(file, attrs);
        }

        public List<File> getFiles() {
            return files;
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        Zip zip = new Zip();
        Path path = Path.of(argsName.get("d"));
        List<File> sources = zip.collect(path, argsName.get("e"));
        File target = new File(argsName.get("o"));
        zip.packFiles(sources, target);
    }
}
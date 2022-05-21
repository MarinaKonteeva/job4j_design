package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            var lines = read.lines()
                    .filter(str -> !str.startsWith("#") && !str.isEmpty())
                    .collect(Collectors.toList());
            for (var line : lines) {
                if (line.startsWith("=")
                        || !line.contains("=")
                        || line.substring(line.indexOf("=") + 1).isEmpty()) {
                    throw new IllegalArgumentException("Нарушение шаблона ключ=значение");
                }
            }

            values.putAll(lines.stream()
                    .collect(Collectors.toMap(k -> k.split("=")[0], v -> v.substring(v.indexOf("=") + 1)))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
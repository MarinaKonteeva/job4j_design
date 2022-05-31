package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("такого ключа нет");
        }
        return values.get(key);
    }

    private boolean valid(String arg) {
        if (arg.isEmpty() || !arg.startsWith("-")
                || arg.startsWith("-=") || !arg.contains("=")) {
            throw new IllegalArgumentException("невалидный аргумент");
        }
        String val = arg.substring(arg.indexOf("=") + 1);
        if (val.isEmpty()) {
            throw new IllegalArgumentException("нет значения");
        }
        return true;
    }

    private void parse(String[] args) {
        for (var arg : args) {
            if (valid(arg)) {
                String key = arg.split("=")[0].substring(1);
                String val = arg.substring(arg.indexOf("=") + 1);
                values.put(key, val);
            }
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("пустой массив");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

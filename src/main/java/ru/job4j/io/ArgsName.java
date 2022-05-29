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

    private void parse(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")
                    && !args[i].startsWith("-=")
                    && args[i].contains("=")) {
                String key = args[i].split("=")[0].substring(1);
                String val = args[i].substring(args[i].indexOf("=") + 1);
                if (val.isEmpty()) {
                    throw new IllegalArgumentException("нет значения");
                }
                values.put(key, val);
            } else {
                throw new IllegalArgumentException("невалидный аргумент");
            }
        }

    }

    public static ArgsName of(String[] args) {
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

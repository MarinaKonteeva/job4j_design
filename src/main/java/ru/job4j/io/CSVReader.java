package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {

    public static void val(String path, String delimiter, String out, Set<String> filter) {

        File file = new File(path);
        if (!file.isFile()) {
            throw new IllegalArgumentException("первый аргумент должен быть путь до папки поиска");
        }
        if (!";".equals(delimiter)) {
            throw new IllegalArgumentException("второй аргумент должен быть расширение файла");
        }
        if (filter.size() == 0) {
            throw new IllegalArgumentException("нет полей");
        }
    }

    public static void handle(ArgsName argsName) throws Exception {
        var path = argsName.get("path");
        var delimiter = argsName.get("delimiter");
        var out = argsName.get("out");
        var filter = Arrays.stream(argsName.get("filter").split(",")).collect(Collectors.toSet());
        val(path, delimiter, out, filter);

        try (var scanner = new Scanner(new FileReader(path));
             PrintWriter pw = "stdout".equals(out)
                     ? new PrintWriter(System.out)
                     : new PrintWriter(new FileWriter(out))) {

            var titles = scanner.nextLine().split(delimiter);
            List<String[]> data = new ArrayList<>();
            while (scanner.hasNext()) {
                data.add(scanner.nextLine().split(delimiter));
            }

            String str = "";
            for (int i = 0; i < titles.length; i++) {
                if (filter.contains(titles[i])) {
                    str += str.length() == 0 ? titles[i] : delimiter + titles[i];
                }
            }
            pw.println(str);

            for (var d : data) {
                str = "";
                for (int i = 0; i < titles.length; i++) {
                    if (filter.contains(titles[i])) {
                        str += str.length() == 0 ? d[i] : delimiter + d[i];
                    }
                }
                pw.println(str);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var argsName = ArgsName.of(args);

        try {
            CSVReader.handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
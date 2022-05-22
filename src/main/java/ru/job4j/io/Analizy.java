package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            var str = in.lines().collect(Collectors.toList());
            boolean work = true;
            String time = "";
            for (var s : str) {
                var ss = s.split(" ");
                int code = Integer.parseInt(ss[0]);
                if (work && (code >= 400 && code < 600)) {
                    time = ss[1];
                    work = false;
                }
                if (!work && !(code >= 400 && code < 600)) {
                    time = time + ";" + ss[1];
                    work = true;
                    rsl.add(time);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            rsl.stream().forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var source  = "./data/server.log";
        var target = "./data/target.log";
        var analizy = new Analizy();
        analizy.unavailable(source, target);
    }
}
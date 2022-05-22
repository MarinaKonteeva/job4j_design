package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            try (PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(target)
                    ))) {
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
                        out.println(time);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
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
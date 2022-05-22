package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            PrintWriter out = new PrintWriter(
                    new BufferedOutputStream(
                            new FileOutputStream(target)
                    ));
            boolean work = true;
            String time = "";
            String s;
            while ((s = in.readLine()) != null) {
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
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var source = "./data/server.log";
        var target = "./data/target.log";
        var analizy = new Analizy();
        analizy.unavailable(source, target);
    }
}
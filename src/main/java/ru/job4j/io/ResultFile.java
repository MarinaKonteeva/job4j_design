package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    out.write(((i + 1) * (j + 1) + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
            out.write(System.lineSeparator().getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

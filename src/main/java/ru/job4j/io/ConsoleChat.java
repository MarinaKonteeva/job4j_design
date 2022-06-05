package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        var answers = readPhrases();
        List<String> log = new ArrayList<>();
        boolean isStop = false;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (!OUT.equals(command)) {

            command = scanner.nextLine();
            log.add(command);

            if (OUT.equals(command)) {
                saveLog(log);
            } else if (STOP.equals(command)) {
                isStop = true;
            } else if (CONTINUE.equals(command)) {
                isStop = false;
                var answer = answers.get(random.nextInt(answers.size()));
                log.add(answer);
                System.out.println(answer);
            } else {
                if (!isStop) {
                    var answer = answers.get(random.nextInt(answers.size()));
                    log.add(answer);
                    System.out.println(answer);
                }
            }
        }
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            rsl = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8")))) {
            for (var l : log) {
                pw.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(".\\data\\log.txt", ".\\data\\Answers.txt");
        cc.run();
    }
}

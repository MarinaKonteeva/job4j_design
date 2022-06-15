package ru.job4j.serialization.json;

import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Student {
    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String[] statuses;

    public Student(boolean sex, int age, Contact contact, String[] statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) {
        final Student student = new Student(true, 39, new Contact("11-111"),
                new String[] {"Worker", "Married"});

        /* Преобразуем объект student в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(student));

        /* Модифицируем json-строку */
        final String studentJson =
                "{"
                        + "\"sex\":true,"
                        + "\"age\":39,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Student studentMod = gson.fromJson(studentJson, Student.class);
        System.out.println(studentMod);
    }
}

package ru.job4j.serialization.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

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

        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Student student1 = new Student(false, 39, new Contact("22-111"),
                new String[] {"Worker", "Married"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", student1.isSex());
        jsonObject.put("age", student1.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(student1).toString());
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

}

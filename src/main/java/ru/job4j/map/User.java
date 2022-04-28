package ru.job4j.map;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(name, user.name)
                && Objects.equals(children, user.children)
                && Objects.equals(birthday, user.birthday);
    }

    public static void main(String[] args) {
        User user = new User("Ivan", 20, new GregorianCalendar(2017, 1, 25));
        User user2 = new User("Ivan", 20, new GregorianCalendar(2017, 1, 25));
        Map<User, Object> map = new HashMap<>();
        map.put(user, new Object());
        map.put(user2, new Object());
        for (User key : map.keySet()) {
            var value = map.get(key);
            System.out.println("key = " + key + ", value = " + value);
        }
    }
}

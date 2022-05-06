package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        for (var cur : current) {
            if (!previous.contains(cur)) {
                added++;
            }
        }

        for (var prev : previous) {
            if (!current.contains(prev)) {
                deleted++;
            }
        }

        for (var cur : current) {
            var prev = previous.stream().filter(w -> w.getId() == cur.getId()).findFirst();
            if (prev.isPresent() && !prev.get().getName().equals(cur.getName())) {
                changed++;
            }
        }

        return new Info(added, changed, deleted);
    }

}

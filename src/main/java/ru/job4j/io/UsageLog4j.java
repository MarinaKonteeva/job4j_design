package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        short sh = 2;
        int i = 3;
        long l = 40000000;
        float f = 1.5f;
        double d = 1.3;
        boolean bool = true;
        char ch = 'a';
        LOG.debug("примитивные типы b : {}, ch : {}, i : {}, l : {}, f : {}, d : {}, bool : {}, ch : {}",
                b, ch, i, l, f, d, bool, ch);
    }
}

package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        try {
            while (true) {
                out.push(in.pop());
            }
        } catch (NoSuchElementException ex) {
            return out.pop();
        }
    }

    public void push(T value) {
        try {
            while (true) {
                in.push(out.pop());
            }
        } catch (NoSuchElementException ex) {
            in.push(value);
        }
    }
}

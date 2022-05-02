package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count > capacity * LOAD_FACTOR) {
            expand();
        }
        int i = hash(key.hashCode());
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode % capacity;
    }

    private int indexFor(int hash) {
        return 0;
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] table1 = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                int j = hash(table[i].key.hashCode());
                table1[j] = table[i];
            }
        }
        table = table1;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int i = hash(key.hashCode());
        if (table[i] != null && table[i].key == key) {
            rsl = table[i].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int i = hash(key.hashCode());
        if (table[i] != null && table[i].key == key) {
            table[i] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int i = 0;
            int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[i] != null && i < count) {
                    i++;
                }
                return i < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[i++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
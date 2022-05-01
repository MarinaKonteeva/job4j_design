package ru.job4j.map;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void testPut() {
        Map<Integer, String> hashTable = new SimpleMap<>();
        assertTrue(hashTable.put(1, "name1"));
        assertTrue(hashTable.put(2, "name2"));
        assertTrue(hashTable.put(3, "name3"));
    }

    @Test
    public void testPutWhenExpend() {
        Map<Integer, String> hashTable = new SimpleMap<>();
        assertTrue(hashTable.put(1, "name1"));
        assertTrue(hashTable.put(2, "name2"));
        assertTrue(hashTable.put(3, "name3"));
        assertTrue(hashTable.put(4, "name4"));
        assertTrue(hashTable.put(5, "name5"));
        assertTrue(hashTable.put(6, "name6"));
        assertTrue(hashTable.put(7, "name7"));
        assertTrue(hashTable.put(8, "name8"));
        assertTrue(hashTable.put(9, "name9"));
    }

    @Test
    public void testGet() {
        Map<Integer, String> hashTable = new SimpleMap<>();
        hashTable.put(1, "name1");
        hashTable.put(2, "name2");
        hashTable.put(3, "name3");
        assertEquals(hashTable.get(2), "name2");
    }

    @Test
    public void testGetNull() {
        Map<Integer, String> hashTable = new SimpleMap<>();
        assertNull(hashTable.get(1));
    }

    @Test
    public void testRemove() {
        Map<Integer, String> hashTable = new SimpleMap<>();
        hashTable.put(1, "name1");
        hashTable.put(2, "name2");
        hashTable.put(3, "name3");
        assertTrue(hashTable.remove(1));
        assertNull(hashTable.get(1));
    }

    @Test
    public void testRemoveWhenExpend() {
        Map<Integer, String> hashTable = new SimpleMap<>();
        assertTrue(hashTable.put(1, "name1"));
        assertTrue(hashTable.put(2, "name2"));
        assertTrue(hashTable.put(3, "name3"));
        assertTrue(hashTable.put(4, "name4"));
        assertTrue(hashTable.put(5, "name5"));
        assertTrue(hashTable.put(6, "name6"));
        assertTrue(hashTable.put(7, "name7"));
        assertTrue(hashTable.put(8, "name8"));
        assertTrue(hashTable.put(9, "name9"));
        assertTrue(hashTable.remove(1));
        assertNull(hashTable.get(1));
    }
}
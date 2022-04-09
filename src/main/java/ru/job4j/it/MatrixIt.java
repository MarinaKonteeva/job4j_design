package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (column > data[row].length - 1) {
            row++;
            column = 0;
            while (row < data.length && data[row].length == 0) {
                row++;
            }
        }
        return row < data.length || row == data.length - 1 && column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i = data[row][column++];
        return i;
    }
}
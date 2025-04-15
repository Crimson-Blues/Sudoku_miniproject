package org.example.miniproyecto2.Model;

public class Hint {
    private final int row;
    private final int col;
    private final int value;

    public Hint(int col, int row, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public int getValue() { return value; }
}

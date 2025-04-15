package org.example.miniproyecto2.Model;

public interface IBoard {
    void setCell(int cellCol, int cellRow, Cell cell);
    Cell getCell(int cellCol, int cellRow);
    boolean isFull();
    boolean isValueValid(int cellCol, int cellRow, int value);
}

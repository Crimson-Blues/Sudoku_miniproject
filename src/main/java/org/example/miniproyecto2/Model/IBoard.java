package org.example.miniproyecto2.Model;

public interface IBoard {
    boolean isCellValid(int cellCol, int cellRow);
    void setCell(int cellCol, int cellRow, Cell cell);
    Cell getCell(int cellCol, int cellRow);
    boolean isFull();
    boolean isValueValid(int cellCol, int cellRow, int value);
}

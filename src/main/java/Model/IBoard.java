package Model;

public interface IBoard {
    boolean isCellValid(int cellRow, int cellCol);
    void setCell(int cellRow, int cellCol, int value);
    int getCell(int cellRow, int cellCol);
    boolean isFull();
    boolean isValueValid(int cellRow, int cellCol, int value);
}

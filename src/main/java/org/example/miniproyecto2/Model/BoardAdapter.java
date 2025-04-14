package org.example.miniproyecto2.Model;

import java.util.ArrayList;
import java.util.List;

public abstract class BoardAdapter implements IBoard{
    protected int width;
    protected int height;
    protected List<List<Cell>> board;

    public BoardAdapter(int width, int height){
        this.width = width;
        this.height = height;
        board = new ArrayList<>();
        for(int i = 0; i < width; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < height; j++) {
                board.get(i).add(new Cell());
            }
        }

    }
    @Override
    public boolean isCellValid(int cellCol, int celLRow) {
        return false;
    }

    @Override
    public boolean isValueValid(int cellRow, int cellCol, int value) {
        return false;
    }

    @Override
    public void setCell(int cellCol, int cellRow, Cell cell) {
        board.get(cellCol).set(cellRow, cell);
    }
    @Override
    public Cell getCell(int cellCol, int cellRow) {
        return board.get(cellCol).get(cellRow);
    }

    @Override
    public boolean isFull(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if (board.get(i).get(j).isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

}

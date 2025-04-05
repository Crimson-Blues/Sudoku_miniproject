package Model;

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
        for(int i = 0; i < width; i++){
            board.add(new ArrayList<>());
        }
        for(int i = 0; i < height; i++){
            board.get(i).add(new Cell());
        }

    }
    @Override
    public boolean isCellValid(int cellRow, int cellCol) {
        return false;
    }

    @Override
    public boolean isValueValid(int cellRow, int cellCol, int value) {
        return false;
    }

    @Override
    public void setCell(int cellRow, int cellCol, int value) {
        board.get(cellRow).get(cellCol).setValue(value);
    }
    @Override
    public int getCell(int cellRow, int cellCol) {
        return board.get(cellRow).get(cellCol).getValue();
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

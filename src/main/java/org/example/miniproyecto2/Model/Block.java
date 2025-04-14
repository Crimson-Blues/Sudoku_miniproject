package org.example.miniproyecto2.Model;

public class Block extends BoardAdapter{

    public Block(int width, int height){
        super(width, height);
    }

    @Override
    public boolean isCellValid(int cellCol, int cellRow){
        if(board.get(cellCol).get(cellRow).isEmpty()){
            return true;
        }
        int checkedCell = board.get(cellCol).get(cellRow).getValue();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if ((i != cellRow || j != cellCol) && board.get(i).get(j).getValue() == checkedCell){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isValueValid(int cellCol, int cellRow, int value){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if (board.get(i).get(j).getValue() == value){
                    return false;
                }
            }
        }
        return true;
    }
}

package Model;

import java.util.ArrayList;
import java.util.List;

public class Block extends BoardAdapter{

    public Block(int width, int height){
        super(width, height);
    }

    @Override
    public boolean isCellValid(int cellRow, int cellCol){
        boolean valid = true;
        if(board.get(cellRow).get(cellCol).isEmpty()){
            return true;
        }
        int checkedCell = board.get(cellRow).get(cellRow).getValue();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if (board.get(i).get(j).getValue() == checkedCell){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isValueValid(int cellRow, int cellCol, int value){
        boolean valid = true;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if (board.get(i).get(j).getValue() == value){
                    return false;
                }
            }
        }

        return true;
    }
}

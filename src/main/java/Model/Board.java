package Model;

import java.util.ArrayList;
import java.util.List;

public class Board extends BoardAdapter{
    List<Block>  blocks;


    public Board(int width, int height){
        super(width, height);
        blocks = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            blocks.add(new Block(2,3));
        }
    }

    public void fillBoard(){
        int rndRow, rndCol;
        for(int i = 0; i < width/3; i++){
            for(int j = 0; j < height/2; j++){
                rndRow = (int)((Math.random() + 1)*(3));
                rndCol = (int)(Math.random() +1 *(2));
                board.get(rndRow).get(rndCol).setValue((int)(Math.random()*(6+2)));
            }
        }
    }

    @Override
    public boolean isCellValid(int cellRow, int cellCol){
        boolean valid = true;
        int checkCell = board.get(cellRow).get(cellCol).getValue();
        if(board.get(cellRow).get(cellCol).isEmpty()){
            return true;
        }

        for(int i = 0; i < width; i++){
            if(board.get(i).get(cellCol).getValue() == checkCell){
                valid = false;
            }
        }

        for(int i = 0; i < height; i++){
            if(board.get(cellRow).get(i).getValue() == checkCell){
                valid = false;
            }
        }

        valid = valid &&
    }

    public int blockPosition(int cellRow, int cellCol){

    }



}

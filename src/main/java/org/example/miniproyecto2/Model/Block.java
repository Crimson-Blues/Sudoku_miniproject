package org.example.miniproyecto2.Model;

public class Block extends BoardAdapter{

    public Block(int width, int height){
        super(width, height);
    }

    @Override
    public boolean isValueValid(int cellCol, int cellRow, int value){
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

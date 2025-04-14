package org.example.miniproyecto2.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends BoardAdapter{
    List<List<Block>>  blocks;


    public Board(int width, int height){
        super(width, height);
        System.out.println("Board Constructor");

        blocks = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            blocks.add(new ArrayList<>());
            for(int j = 0; j < 3; j++){
                blocks.get(i).add(new Block(3,2));
            }
        }

        int blockRow, blockCol;
        //Assign the board cells to the blocks
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                blockCol = i/3;
                blockRow = j/2;

                blocks.get(blockCol).get(blockRow).setCell(i%3,j%2,board.get(i).get(j));
            }
        }

        fillBoard();
        System.out.println(board);
    }

    public void fillBoard(){
        int rndRow, rndCol, value;
        Random rand = new Random();
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                rndCol = rand.nextInt(3) + i*3;
                rndRow = rand.nextInt(2) + j*2 ;
                do{
                    value = rand.nextInt(6) + 1;
                }while(!board.get(rndCol).get(rndRow).isValueValid(value));
                System.out.println(value);
                board.get(rndCol).get(rndRow).setValue(value);

            }
        }
    }


    @Override
    public boolean isCellValid(int cellCol, int cellRow){
        boolean valid = true;
        int checkCell = board.get(cellCol).get(cellRow).getValue();
        if(board.get(cellCol).get(cellRow).isEmpty()){
            return true;
        }

        for(int i = 0; i < width; i++){
            if(board.get(i).get(cellRow).getValue() == checkCell){
                valid = false;
            }
        }

        for(int i = 0; i < height; i++){
            if(board.get(cellCol).get(i).getValue() == checkCell){
                valid = false;
            }
        }
        int blockCol = cellCol/3;
        int blockRow = cellRow/2;

        valid = valid && blocks.get(blockCol).get(blockRow).isCellValid(cellCol,cellRow);

        return valid;
    }

    @Override
    public boolean isValueValid(int cellCol, int cellRow, int value){
        boolean valid = true;

        // Para q no aparezca un error al borrar el numero
        int checkCell = board.get(cellCol).get(cellRow).getValue();
        if(board.get(cellCol).get(cellRow).isEmpty()){
            return true;
        }

        for(int i = 0; i < width; i++){
            if(board.get(i).get(cellRow).getValue() == value){
                valid = false;
            }
        }

        for(int i = 0; i < height; i++){
            if(board.get(cellCol).get(i).getValue() == value){
                valid = false;
            }
        }
        int blockCol = cellCol/3;
        int blockRow = cellRow/2;

        valid = valid && blocks.get(blockCol).get(blockRow).isValueValid(cellCol,cellRow, value);

        return valid;
    }






}
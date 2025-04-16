package org.example.miniproyecto2.Model;

import java.util.*;

public class Board extends BoardAdapter{
    List<List<Block>>  blocks;
    List<Cell> emptyCells;
    List<Cell> initialCells;


    public Board(int width, int height){
        super(width, height);
        //Array for the remaining empty cells
        emptyCells = new  ArrayList<>();
        initialCells = new  ArrayList<>();

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
        updateEmptyCells();
    }

    public void fillBoard(){
        //Resets initial cell lists
        initialCells.clear();

        int rndRow, rndCol, value;
        Random rand = new Random();
        for (int nums = 0; nums < 2; nums++) {

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    do{
                        rndCol = rand.nextInt(3) + i * 3;
                        rndRow = rand.nextInt(2) + j * 2;
                    }while(!board.get(rndCol).get(rndRow).isEmpty());

                    do {
                        value = rand.nextInt(6) + 1;
                    } while(!isValueValid(rndCol,rndRow,value));

                    board.get(rndCol).get(rndRow).setValue(value);
                    initialCells.add(board.get(rndCol).get(rndRow));

                }
            }

        }
    }


    @Override
    public boolean isValueValid(int cellCol, int cellRow, int value){
        if(value == 0){
            return true;
        }
        //Checks for same value in the cell's row
        for(int i = 0; i < 6; i++){
            if(board.get(i).get(cellRow).getValue() == value){
                return false;
            }
        }

        //Checks for same value in cell's column
        for(int i = 0; i < 6; i++){
            if(board.get(cellCol).get(i).getValue() == value){
                return false;
            }
        }
        int blockCol = cellCol/3;
        int blockRow = cellRow/2;


        return blocks.get(blockCol).get(blockRow).isValueValid(cellCol%3,cellRow%2, value);
    }

    @Override
    public boolean isCellValid(int cellCol, int cellRow){
        int value = getCell(cellCol, cellRow).getValue();

        if(value == 0){
            return true;
        }
        //Checks for same value in the cell's row
        for(int i = 0; i < width; i++){
            if(i == cellCol){
                continue;
            }
            if(board.get(i).get(cellRow).getValue() == value){
                return false;
            }
        }

        //Checks for same value in cell's column
        for(int i = 0; i < height; i++){
            if(i == cellRow){
                continue;
            }
            if(board.get(cellCol).get(i).getValue() == value){
                return false;
            }
        }
        int blockCol = cellCol/3;
        int blockRow = cellRow/2;

        return blocks.get(blockCol).get(blockRow).isCellValid(cellCol%3,cellRow%2);
    }



    public Optional<Hint> getHint(){
        updateEmptyCells();
        Collections.shuffle(emptyCells, new Random());
        for(Cell cell : emptyCells){
            for(int i = 1; i < 7; i++){
                if(isValueValid(cell.getCol(),cell.getRow(), i)){
                    return Optional.of(new Hint(cell.getCol(),cell.getRow(),i));
                }
            }
        }

        return Optional.empty();
    }

    public void updateEmptyCells(){
        emptyCells.clear();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(board.get(i).get(j).isEmpty()){
                    emptyCells.add(board.get(i).get(j));
                }
            }
        }

    }

    public void resetBoard(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(!initialCells.contains(board.get(i).get(j))){
                    board.get(i).get(j).setValue(0);
                }
            }
        }
    }

    public boolean isBoardComplete() {
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                if(!isCellValid(col, row)){
                    System.out.println("Cell [" + col + "][" + row + "] is invalid");
                    return false;
                }
            }
        }
        System.out.println("Is Board Full: " + isFull());
        return isFull();
    }

    public void testBoard(){
        int[][] solvedValues = {
                {5, 2, 4, 6, 1, 3},
                {1, 3, 6, 4, 5, 2},
                {2, 6, 1, 5, 3, 4},
                {3, 4, 5, 2, 6, 1},
                {6, 1, 2, 3, 4, 5},
                {4, 5, 3, 1, 2, 0}
        };

        clear();

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                getCell(i,j).setValue(solvedValues[j][i]);
            }
        }
    }






}
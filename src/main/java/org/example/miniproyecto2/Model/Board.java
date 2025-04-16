package org.example.miniproyecto2.Model;

import java.util.*;

/**
 * Represents the complete Sudoku board.
 * <p>This class extends {@link BoardAdapter} and includes logic for setting up the board,
 * validating cells and values, filling the board with random numbers, checking board completion, and providing hints.</p>
 */
public class Board extends BoardAdapter{
    /**
     * A 2D list of blocks on the board, used for validating block-level constraints.
     */
    List<List<Block>>  blocks;
    /**
     * A list of empty cells on the board.
     * These are the cells that are yet to be filled.
     */
    List<Cell> emptyCells;
    /**
     * A list of initial cells that have fixed values on the board.
     * These cells cannot be modified.
     */
    List<Cell> initialCells;


    /**
     * Constructs a new Board with the specified width and height.
     * <p>The board is initialized with a set of blocks, cells, and initial values.</p>
     *
     * @param width the number of columns in the board
     * @param height the number of rows in the board
     */
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

    /**
     * Fills the board with random valid values for some of the cells.
     * <p>2 random values per block are placed into the cells in a way that respects Sudoku rules.
     * The initial cells are recorded and cannot be modified.</p>
     */
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


    /**
     * Validates whether a given value can be placed in the specified cell.
     * <p>This method checks the row, column, and block to ensure no conflicts with existing values.</p>
     *
     * @param cellCol the column index of the cell (0-based)
     * @param cellRow the row index of the cell (0-based)
     * @param value the value to validate
     * @return true if the value can be placed, false if it conflicts with existing values
     */
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

    /**
     * Validates whether the specified cell is valid in terms of its current value.
     * <p>This method checks the row, column, and block to ensure no conflicts with other cells.</p>
     *
     * @param cellCol the column index of the cell (0-based)
     * @param cellRow the row index of the cell (0-based)
     * @return true if the cell is valid, false otherwise
     */
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



    /**
     * Provides a hint for a random empty cell with a valid value.
     *
     * @return an {@link Optional} containing a {@link Hint} if a valid value is found for an empty cell,
     *         or {@link Optional#empty()} if no valid values are found
     */
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

    /**
     * Updates the list of empty cells on the board.
     * This method will clear the current list and add all cells that are empty.
     */
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

    /**
     * Resets the board to its initial state, clearing all non-fixed cells.
     * The cells that were initially filled cannot be modified.
     */
    public void resetBoard(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(!initialCells.contains(board.get(i).get(j))){
                    board.get(i).get(j).setValue(0);
                }
            }
        }
    }

    /**
     * Checks if the board is complete and all cells are valid.
     *
     * @return true if the board is complete and valid, false otherwise
     */
    public boolean isBoardComplete() {
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                if(!isCellValid(col, row)){
                    System.out.println("Cell [" + col + "][" + row + "] is invalid");
                    return false;
                }
            }
        }
        return isFull();
    }

    /**
     * Tests the board by filling it with a predefined solved Sudoku grid.
     */
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
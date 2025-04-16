package org.example.miniproyecto2.Model;

/**
 * Represents a block (subgrid) in a Sudoku board.
 *
 * <p>This class extends {@link BoardAdapter} and provides additional validation logic for values and cells
 * specific to the Sudoku block. A block contains a set of cells, commonly 3x3 but depends on the given the board layout.</p>
 */
public class Block extends BoardAdapter{

    /**
     * Constructs a new Block with the specified width and height.
     *
     * <p>The block is initialized as a 2D grid of cells based on the provided dimensions.</p>
     *
     * @param width the number of columns in the block
     * @param height the number of rows in the block
     */
    public Block(int width, int height){
        super(width, height);
    }

    /**
     * Validates if a given value can be placed in the specified cell in the block.
     *
     * <p>This method checks if the value already exists in any cell within the block.
     * If the value is already present in the block, the method returns false; otherwise, it returns true.</p>
     *
     * @param cellCol the column index of the cell (0-based)
     * @param cellRow the row index of the cell (0-based)
     * @param value the value to validate
     * @return true if the value is valid for the cell, false if the value already exists in the block
     */
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

    /**
     * Validates if the specified cell is valid in the block.
     *
     * <p>This method checks if the value in the specified cell already exists in any other cell of the block.
     * If the value is already present in the block, the method returns false; otherwise, it returns true.</p>
     *
     * @param cellCol the column index of the cell (0-based)
     * @param cellRow the row index of the cell (0-based)
     * @return true if the cell is valid (i.e., no duplicate values exist in the block), false otherwise
     */
    @Override
    public boolean isCellValid(int cellCol, int cellRow){
        int value = getCell(cellCol, cellRow).getValue();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(i == cellCol && j == cellRow){
                    continue;
                }
                if(board.get(i).get(j).getValue() == value){
                    return false;
                }
            }
        }

        return true;
    }
}

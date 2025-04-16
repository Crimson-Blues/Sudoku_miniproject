package org.example.miniproyecto2.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract implementation of the {@link IBoard} interface.
 *
 * <p>This class provides a basic structure for a Sudoku board,
 * with methods to manipulate the board's cells, validate values, and check the board's state.</p>
 */
public abstract class BoardAdapter implements IBoard{
    /**
     * The width (number of columns) of the board.
     */
    protected int width;
    /**
     * The height (number of rows) of the board.
     */
    protected int height;
    /**
     * A 2D list representing the cells of the board.
     * Each cell is an instance of the {@link Cell} class.
     */
    protected List<List<Cell>> board;
    /**
     * Constructs a new BoardAdapter with the specified width and height.
     *
     * <p>The board is initialized as a 2D array of empty {@link Cell} objects.</p>
     *
     * @param width the number of columns in the board
     * @param height the number of rows in the board
     */
    public BoardAdapter(int width, int height){
        this.width = width;
        this.height = height;

        board = new ArrayList<>();
        for(int i = 0; i < width; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < height; j++) {
                board.get(i).add(new Cell(i, j));
            }
        }

    }

    /**
     * Validates if a given value is valid for the specified cell.
     * <p>This implementation always returns false, and should be overridden in subclasses.</p>
     *
     * @param cellRow the row index of the cell (0-based)
     * @param cellCol the column index of the cell (0-based)
     * @param value the value to validate
     * @return false by default
     */
    @Override
    public boolean isValueValid(int cellRow, int cellCol, int value) {
        return false;
    }

    /**
     * Validates if the specified cell is valid inside the board.
     * <p>This implementation always returns false, and should be overridden in subclasses.</p>
     *
     * @param cellRow the row index of the cell (0-based)
     * @param cellCol the column index of the cell (0-based)
     * @return false by default
     */
    @Override
    public boolean isCellValid(int cellCol, int cellRow) {
        return false;
    }
    /**
     * Sets the value of a specific cell on the board.
     *
     * @param cellCol the column index of the cell (0-based)
     * @param cellRow the row index of the cell (0-based)
     * @param cell the {@link Cell} object to set at the specified position
     */
    @Override
    public void setCell(int cellCol, int cellRow, Cell cell) {
        board.get(cellCol).set(cellRow, cell);
    }
    /**
     * Retrieves the cell at the specified position on the board.
     *
     * @param cellCol the column index of the cell (0-based)
     * @param cellRow the row index of the cell (0-based)
     * @return the {@link Cell} at the specified position
     */
    @Override
    public Cell getCell(int cellCol, int cellRow) {
        return board.get(cellCol).get(cellRow);
    }
    /**
     * Checks if the board is completely filled with non-empty cells.
     *
     * @return true if all cells are filled, false otherwise
     */
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

    /**
     * Clears all cells on the board, setting them back to their initial empty state.
     */
    @Override
    public void clear(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                getCell(i,j).clearValue();
            }
        }
    }

}

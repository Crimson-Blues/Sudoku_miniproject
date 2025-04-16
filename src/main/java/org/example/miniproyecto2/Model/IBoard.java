package org.example.miniproyecto2.Model;

/**
 * Interface representing a Sudoku board.
 *
 * <p>This interface defines the methods required for interacting with a Sudoku board,
 * including setting and getting cell values, validating cell values, and clearing the board.</p>
 */
public interface IBoard {
    /**
     * Sets the value of a specific cell on the board.
     *
     * @param cellCol the column index of the cell (0-based)
     * @param cellRow the row index of the cell (0-based)
     * @param cell the {@link Cell} object to be set at the specified position
     */
    void setCell(int cellCol, int cellRow, Cell cell);
    /**
     * Retrieves the cell at the specified position on the board.
     *
     * @param cellCol the column index of the cell (0-based)
     * @param cellRow the row index of the cell (0-based)
     * @return the {@link Cell} at the specified position
     */
    Cell getCell(int cellCol, int cellRow);
    /**
     * Checks if the board is completely filled.
     *
     * @return true if all cells on the board are filled, false otherwise
     */
    boolean isFull();
    /**
     * Validates if the given value is allowed in the specified cell.
     *
     * @param cellCol the column index of the cell (0-based)
     * @param cellRow the row index of the cell (0-based)
     * @param value the value to validate
     * @return true if the value is valid for the given cell, false otherwise
     */
    boolean isValueValid(int cellCol, int cellRow, int value);
    /**
     * Validates if the specified cell is valid inside the board.
     *
     * @param cellCol the column index of the cell (0-based)
     * @param cellRow the row index of the cell (0-based)
     * @return true if the cell is valid, false otherwise
     */
    boolean isCellValid(int cellCol, int cellRow);
    /**
     * Clears all values on the board, resetting the board to its initial state.
     */
    void clear();
}

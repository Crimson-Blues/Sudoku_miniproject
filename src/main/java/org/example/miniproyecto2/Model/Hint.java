package org.example.miniproyecto2.Model;

/**
 * Represents a hint for the Sudoku board.
 *
 * <p>A hint provides a suggested value for a specific cell,
 * typically used to pre-fill the board or assist the user.</p>
 */
public class Hint {
    /**
     * The row index of the cell where the hint should be placed (0-based).
     */
    private final int row;
    /**
     * The column index of the cell where the hint should be placed (0-based).
     */
    private final int col;
    /**
     * The value suggested by the hint.
     */
    private final int value;
    /**
     * Constructs a Hint object with the given position and value.
     *
     * @param col the column index of the hint
     * @param row the row index of the hint
     * @param value the suggested value for the cell
     */
    public Hint(int col, int row, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    /**
     * Returns the row index of the hint.
     *
     * @return the row index
     */
    public int getRow() { return row; }
    /**
     * Returns the column index of the hint.
     *
     * @return the column index
     */
    public int getCol() { return col; }
    /**
     * Returns the suggested value of the hint.
     *
     * @return the hint value
     */
    public int getValue() { return value; }
}

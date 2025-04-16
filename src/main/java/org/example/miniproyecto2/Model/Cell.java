package org.example.miniproyecto2.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.InputMismatchException;

/**
 * Represents a single cell in a Sudoku grid.
 *
 * <p>A cell contains a value and its position (row and column) within the board.
 * It supports JavaFX property bindings and includes utility methods for validation and clearing.</p>
 */
public class Cell{
    /**
     * The current value of the cell.
     * A value of 0 represent the null value indicating that the cell is empty.
     */
    private final IntegerProperty value = new SimpleIntegerProperty();
    /**
     * The row position of the cell in the Sudoku board (0-based).
     */
    private int row;
    /**
     * The column position of the cell in the Sudoku board (0-based).
     */
    private int col;
    /**
     * Constructs an empty cell with default value 0.
     */
    public Cell(){
        value.set(0);
    }

    /**
     * Constructs an empty cell at the specified position.
     *
     * @param col the column index of the cell
     * @param row the row index of the cell
     */
    public Cell(int col, int row){
        value.set(0);
        this.col = col;
        this.row = row;
    }

    /**
     * Constructs a cell with the given value and position.
     *
     * @param value the initial value of the cell
     * @param col the column index of the cell
     * @param row the row index of the cell
     */
    public Cell(int value, int col, int row){
        this.value.set(value);
        this.col = col;
        this.row = row;
    }
    /**
     * Returns the current value of the cell.
     *
     * @return the value of the cell
     */
    public int getValue(){
        return value.get();
    }

    /**
     * Returns the row index of the cell.
     *
     * @return the row index
     */
    public int getRow(){
        return row;
    }
    /**
     * Returns the column index of the cell.
     *
     * @return the column index
     */
    public int getCol(){
        return col;
    }

    /**
     * Sets the value of the cell.
     *
     * @param value the new value to set
     */
    public void setValue(int value){
        this.value.set(value);
    }

    /**
     * Checks whether the cell is empty (value is 0).
     *
     * @return true if the cell is empty, false otherwise
     */
    public boolean isEmpty(){
        return value.get() == 0;
    }

    /**
     * Clears the cell value (sets it to 0).
     */
    public void clearValue() {
        value.set(0);
    }

    /**
     * Returns the JavaFX property for binding or listening to value changes.
     *
     * @return the IntegerProperty representing the cell's value
     */
    public IntegerProperty valueProperty() {
        return value;
    }

}

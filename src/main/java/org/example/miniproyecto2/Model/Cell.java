package org.example.miniproyecto2.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.InputMismatchException;

public class Cell{
    private final IntegerProperty value = new SimpleIntegerProperty();
    private int row;
    private int col;

    public Cell(){
        value.set(0);
    }

    public Cell(int col, int row){
        value.set(0);
        this.col = col;
        this.row = row;
    }

    public Cell(int value, int col, int row){
        this.value.set(value);
        this.col = col;
        this.row = row;
    }

    public int getValue(){
        return value.get();
    }

    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }

    public void setValue(int value){
        this.value.set(value);
    }

    public boolean isEmpty(){
        return value.get() == 0;
    }

    public boolean isValueValid(Integer value){
        return value <= 6 && value >= 0;
    }

    public void clearValue() {
        value.set(0);
    }

    public IntegerProperty valueProperty() {
        return value;
    }

}

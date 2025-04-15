package org.example.miniproyecto2.Model;

import java.util.InputMismatchException;

public class Cell{
    private Integer value;
    private int row;
    private int col;

    public Cell(){
        value = null;
    }

    public Cell(int col, int row){
        value = null;
        this.col = col;
        this.row = row;
    }

    public Cell(int value, int col, int row){
        try{
            if(!isValueValid(value)){
                throw new InputMismatchException("Sólo se permiten valores del 1 al 6");
            }
            else{
                this.value = value;
            }

        } catch (InputMismatchException e) {
            throw new RuntimeException(e);
        }

        this.row = row;
        this.col = col;
    }

    public int getValue(){
        int val = value != null ? value : 0;
        return val;
    }

    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }

    public void setValue(int value){
        try{
            if(!isValueValid(value)){
                throw new InputMismatchException("Sólo se permiten valores del 1 al 6");
            }
            else{
                this.value = value;
            }

        } catch (InputMismatchException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isEmpty(){
        return value == null;
    }

    public boolean isValueValid(Integer value){
        return value <= 6 && value >= 1;
    }

    public void clearValue() {
        this.value = null;
    }

}

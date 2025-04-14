package org.example.miniproyecto2.Model;

import java.util.InputMismatchException;

public class Cell{
    private Integer value;

    public Cell(){
        value = null;
    }

    public Cell(int value){
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

    public int getValue(){
        return value != null ? value : 0;
        //return value;
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

package org.example.miniproyecto2.Controller;

import javafx.util.StringConverter;

public class CellValueConverter extends StringConverter<Integer> {


    @Override
    public String toString(Integer integer) {
        if(integer == null || integer == 0){
            return "";
        }

        return  integer.toString();
    }

    @Override
    public Integer fromString(String s) {
        if (s == null || s.isBlank()) {
            return 0;
        }

        return Integer.parseInt(s);
    }

}

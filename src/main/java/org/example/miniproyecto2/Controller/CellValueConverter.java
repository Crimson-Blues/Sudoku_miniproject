package org.example.miniproyecto2.Controller;

import javafx.util.StringConverter;

public class CellValueConverter extends StringConverter<Number> {

    @Override
    public String toString(Number number) {
        if(number == null || number.intValue() == 0){
            return "";
        }

        return  number.toString();
    }

    @Override
    public Number fromString(String s) {
        if (s == null || s.isBlank()) {
            return 0;
        }

        return Integer.parseInt(s);
    }

}

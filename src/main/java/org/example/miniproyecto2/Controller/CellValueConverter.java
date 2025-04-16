package org.example.miniproyecto2.Controller;

import javafx.util.StringConverter;

/**
 * Converts between {@link Integer} values and their {@link String} representations
 * for use in JavaFX controls like {@link javafx.scene.control.TextField}.
 * <p>
 * Used in the Sudoku game to manage the conversion between model cells and user input.
 * </p>
 * <ul>
 *     <li>{@code 0} and {@code null} are displayed as an empty string.</li>
 *     <li>Blank or empty user input is interpreted as {@code 0}.</li>
 * </ul>
 */
public class CellValueConverter extends StringConverter<Integer> {

    /**
     * Converts an {@link Integer} to a {@link String} for display in the UI.
     *
     * @param integer the integer value to convert
     * @return a string representing the integer; empty if the value is {@code null} or {@code 0}
     */
    @Override
    public String toString(Integer integer) {
        if(integer == null || integer == 0){
            return "";
        }

        return  integer.toString();
    }

    /**
     * Converts a {@link String} input from the UI into an {@link Integer} value.
     *
     * @param s the string to convert
     * @return the parsed integer value, or {@code 0} if the string is {@code null} or blank
     * @throws NumberFormatException if the string is not blank but cannot be parsed to an integer
     */
    @Override
    public Integer fromString(String s) {
        if (s == null || s.isBlank()) {
            return 0;
        }

        return Integer.parseInt(s);
    }

}

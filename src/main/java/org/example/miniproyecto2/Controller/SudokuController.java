package org.example.miniproyecto2.Controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import org.example.miniproyecto2.Model.Board;
import org.example.miniproyecto2.Model.Cell;

public class SudokuController {

    @FXML
    private GridPane boardGridPane;

    private static final int GRID_SIZE = 6;
    private TextField[][] cells = new TextField[GRID_SIZE][GRID_SIZE];
    private Board board;

    @FXML
    public void initialize() {
        board = new Board(GRID_SIZE, GRID_SIZE);
        board.fillBoard(); // Llena automáticamente el tablero

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                TextField cellField = createCell(col, row);
                cells[col][row] = cellField;
                boardGridPane.add(cellField, col, row);
            }
        }
    }

    private TextField createCell(int col, int row) {
        TextField textField = new TextField();

        textField.setPrefWidth(50);
        textField.setPrefHeight(50);
        textField.setAlignment(Pos.CENTER);
        textField.setStyle("-fx-font-size: 16; -fx-border-color: lightgray;");

        Cell cell = board.getCell(col, row);

        if (!cell.isEmpty()) {
            textField.setText(String.valueOf(cell.getValue()));
            textField.setDisable(true);
        } else {
            textField.setOnKeyTyped(event -> {
                String key = event.getCharacter();

                if (key.matches("[1-6]")) {
                    int value = Integer.parseInt(key);

                    if (board.isValueValid(col, row, value)) {
                        cell.setValue(value);
                        textField.setText(key);
                        textField.setStyle("-fx-font-size: 16; -fx-background-color: white;");
                        textField.setTooltip(null);
                    } else {
                        textField.setText(key);
                        textField.setStyle("-fx-font-size: 16; -fx-background-color: lightcoral;");
                        textField.setTooltip(new Tooltip("Número inválido en fila, columna o bloque"));
                    }

                } else if (key.isBlank()) {
                    cell.clearValue();
                    textField.clear();
                    textField.setStyle("-fx-font-size: 16; -fx-background-color: white;");
                    textField.setTooltip(null);
                }

            });
        }

        return textField;
    }
}

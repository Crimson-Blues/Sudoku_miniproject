package org.example.miniproyecto2.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.example.miniproyecto2.Model.Board;
import org.example.miniproyecto2.Model.Cell;
import org.example.miniproyecto2.Model.Hint;

import java.util.Optional;

public class SudokuController {
    @FXML
    private GridPane boardGridPane;
    @FXML
    private Button helpButton;

    @FXML
    private AnchorPane mainPane;
    private static final int GRID_SIZE = 6;
    private TextField[][] textFields = new TextField[GRID_SIZE][GRID_SIZE];
    private Board board;

    @FXML
    public void initialize() {
        //Creates and fills the board
        //System.out.println(getClass().getResource("/org/example/miniproyecto2/images/fondo.png"));

        board = new Board(GRID_SIZE, GRID_SIZE);

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                TextField cellField = createCell(col, row);
                textFields[col][row] = cellField;
                boardGridPane.add(cellField, col, row);
            }
        }

        handleHelpButton();

    }

    private TextField createCell(int col, int row) {
        TextField textField = new TextField();

        textField.setPrefWidth(50);
        textField.setPrefHeight(50);
        textField.setAlignment(Pos.CENTER);
        textField.setBackground(Background.EMPTY);
        textField.setBorder(Border.EMPTY);

        textField.setStyle("-fx-font-size: 16; -fx-font-family: 'Arial';  -fx-text-fill: black; -fx-opacity: 1.0;");

        Cell cell = board.getCell(col, row);

        if (!cell.isEmpty()) {
            textField.setText(String.valueOf(cell.getValue()));
            textField.setEditable(false);
        } else {
            textField.setOnKeyTyped(event -> {
                String key = event.getCharacter();

                if (key.matches("[1-6]")) {
                    int value = Integer.parseInt(key);

                    if (board.isValueValid(col, row, value)) {
                        cell.setValue(value);
                        textField.setText(key);
                        textField.setStyle("-fx-font-size: 16; -fx-background-color: transparent;");
                    } else {
                        textField.setText(key);
                        textField.setStyle("-fx-font-size: 16; -fx-border-color: lightcoral;");
                        textField.setTooltip(new Tooltip("Número inválido en fila, columna o bloque"));
                    }

                } else if (key.isBlank()) {
                    cell.clearValue();
                    textField.clear();
                    textField.setStyle("-fx-font-size: 16; -fx-background-color: transparent; -fx-border-color: transparent;");
                    textField.setTooltip(null);
                } else {
                    textField.setText("");
                }

            });
        }

        return textField;
    }

    public void handleHelpButton(){
        helpButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

                Optional<Hint> hint = board.getHint();
                if (hint.isPresent()) {
                    int col = hint.get().getCol();
                    int row = hint.get().getRow();
                    int value = hint.get().getValue();
                    //System.out.println("Hint:");
                    //System.out.println(col + " " + row + " " + value);
                    //textFields[col][row].setPromptText(Integer.toString(value));
                    board.getCell(col, row).setValue(value);
                    textFields[col][row].setText(String.valueOf(value));
                    textFields[col][row].setStyle("-fx-font-size: 16; -fx-text-fill: black;");
                    setTextFieldBorder(textFields[col][row], "d9de54");
                }
                else{
                    helpButton.setDisable(true);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ayuda");
                    alert.setHeaderText(null);
                    alert.setContentText("El Sudoku No tiene solución :(");
                    alert.getDialogPane().setStyle(
                            "-fx-font-size: 14px; -fx-font-family: 'Comic Sans MS'; -fx-background-color: #f5e6ff;"
                    );
                    alert.showAndWait();
                    System.out.println("Chingamos no tiene solución");
                }
            }

        });
    }

    public void setTextFieldBorder(TextField tf, String color) {
        String existingStyle = tf.getStyle();
        //Remove previous border styles
        String cleanedStyle = existingStyle.replaceAll("-fx-border-color: [^;]+;", "");
        tf.setStyle(cleanedStyle + "-fx-border-color: " + color + ";");
    }


}

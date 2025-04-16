package org.example.miniproyecto2.Controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import org.example.miniproyecto2.Model.Board;
import org.example.miniproyecto2.Model.Cell;
import org.example.miniproyecto2.Model.Hint;

import java.io.IOException;
import java.util.Optional;

public class SudokuController {
    @FXML
    private GridPane boardGridPane;
    @FXML
    private Button helpButton;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button restartButton;
    @FXML
    private Button newBoardButton;
    private static final int GRID_SIZE = 6;
    private TextField[][] textFields = new TextField[GRID_SIZE][GRID_SIZE];
    private Board board;

    @FXML
    public void initialize() {
        //Creates and fills the board
        board = new Board(GRID_SIZE, GRID_SIZE);
        initializeTextFields();
        handleButtons();

    }

    private void initializeTextFields() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                textFields[col][row] = createCell(col, row);
                boardGridPane.add(textFields[col][row], col, row);
            }
        }
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

        //Binds the textfield text to the cells value unidirectionally
        textField.textProperty().bind(
                Bindings.createStringBinding(
                        () -> new CellValueConverter().toString(cell.valueProperty().get()),
                        cell.valueProperty()
                )
        );
        if(!cell.isEmpty()){
            textField.setEditable(false);
        }
        textField.setOnKeyTyped(event -> {
            if (!textField.isEditable()) return;
            String key = event.getCharacter();

            if (key.matches("[0-6]")) {
                int value = Integer.parseInt(key);
                cell.setValue(value);

                if (board.isCellValid(col, row)) {
                    textField.setStyle("-fx-font-size: 16; -fx-background-color: transparent;");
                    System.out.println("Is Board complete: " + board.isBoardComplete());

                    if (board.isBoardComplete()) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/miniproyecto2/victory-view.fxml"));
                            Scene victoryScene = new Scene(loader.load());
                            Stage stage = (Stage) boardGridPane.getScene().getWindow();
                            stage.setScene(victoryScene);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    textField.setStyle("-fx-font-size: 16; -fx-border-color: lightcoral;");
                    textField.setTooltip(new Tooltip("Número inválido en fila, columna o bloque"));
                }


            } else if (textField.getText().isEmpty()) {
                cell.clearValue();
                textField.setStyle("-fx-font-size: 16; -fx-background-color: transparent; -fx-border-color: transparent;");
                textField.setTooltip(null);
            } else if(event.getCode() == KeyCode.BACK_SPACE){
                cell.clearValue();
            } else{
                System.out.println("Input Invalido");
            }

        });

        return textField;
    }

    private void updateTextFields() {
        Cell cell;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cell = board.getCell(col, row);
                textFields[col][row].setEditable(true);
                if(!cell.isEmpty()){
                    textFields[col][row].setEditable(false);
                }
            }
        }
    }

    public void handleButtons(){
        helpButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

                Optional<Hint> hint = board.getHint();
                if (hint.isPresent()) {
                    int col = hint.get().getCol();
                    int row = hint.get().getRow();
                    int value = hint.get().getValue();

                    board.getCell(col, row).setValue(value);
                    textFields[col][row].setStyle("-fx-font-size: 16; -fx-text-fill: black;");
                    setTextFieldBorder(textFields[col][row], "d9de54");
                }
                else{
                    //helpButton.setDisable(true);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ayuda");
                    alert.setHeaderText(null);
                    alert.setContentText("El Sudoku No tiene solución :(");
                    alert.getDialogPane().setStyle(
                            "-fx-font-size: 14px; -fx-font-family: 'Comic Sans MS'; -fx-background-color: #f5e6ff;"
                    );
                    alert.showAndWait();
                }
            }

        });

        restartButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                board.resetBoard();
            }

            });

        newBoardButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //board.clear();
                //board.fillBoard();
                board.testBoard();
                updateTextFields();
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

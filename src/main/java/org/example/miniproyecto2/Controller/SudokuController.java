package org.example.miniproyecto2.Controller;

import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
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
    @FXML
    private Label errorLabel;
    private static final int GRID_SIZE = 6;
    private TextField[][] textFields = new TextField[GRID_SIZE][GRID_SIZE];
    private CellErrorAnimator[][] errorAnimators = new CellErrorAnimator[GRID_SIZE][GRID_SIZE];
    private CellHelpAnimator[][] helpAnimators = new CellHelpAnimator[GRID_SIZE][GRID_SIZE];
    private Board board;
    private String textFieldBaseStyle;

    @FXML
    public void initialize() {
        //Creates and fills the board
        board = new Board(GRID_SIZE, GRID_SIZE);
        initializeTextFields();
        handleButtons();
        errorLabel.setText("");
    }

    private void initializeTextFields() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                textFields[col][row] = createCell(col, row);
                errorAnimators[col][row] = new CellErrorAnimator(textFields[col][row]);
                helpAnimators[col][row] = new CellHelpAnimator(textFields[col][row]);

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
        textField.setTextFormatter(TextFilter.getFilter());

        textField.setStyle("-fx-font-size: 20; -fx-font-family: 'Arial';  -fx-text-fill: '8967B3'; -fx-opacity: 1.0; -fx-font-weight: normal;");
        textFieldBaseStyle = textField.getStyle();

        Cell cell = board.getCell(col, row);
        textField.setText(new CellValueConverter().toString(cell.getValue()));

        if(!cell.isEmpty()){
            textField.setStyle("-fx-font-size: 20; -fx-font-family: 'Arial';  -fx-text-fill: '624E88'; -fx-opacity: 1.0; -fx-font-weight: bold; -fx-border-color: 'transparent'; -fx-border-width: 1.5;");
        }

        textField.setOnKeyTyped(event -> {
            if (!textField.isEditable()) return;
            String key = event.getCharacter();

            if (key.matches("[1-6]")) {
                int value = Integer.parseInt(key);

                cell.setValue(new CellValueConverter().fromString(textField.getText()));
                textField.setText(new CellValueConverter().toString(cell.getValue()));

                if (board.isCellValid(col, row)) {
                    setValidTextField(col, row);

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
                    setInvalidTextField(col, row);
                    textField.setTooltip(new Tooltip("Número inválido en fila, columna o bloque"));
                    setErrorMessage("Casilla inválida... revisa bien");
                }

            }else{
                setErrorMessage("Cáracter inválido!");
            }


        });

        //Listener to update textfields border
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("[1-6]")) {
                int value = (int) new CellValueConverter().fromString(newValue);
                if (board.isCellValid(col, row)) {
                    setValidTextField(col, row);
                } else {
                    setInvalidTextField(col, row);
                }
            } else if (textField.getText().isEmpty()) {
                cell.clearValue();
                textField.setText(new CellValueConverter().toString(cell.getValue()));
                setValidTextField(col, row);
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
                setValidTextField(col, row);
                textFields[col][row].setText(new CellValueConverter().toString(cell.getValue()));
                if(!cell.isEmpty()){
                    setInitialTextField(col, row);
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

                    Cell cell = board.getCell(col, row);

                    cell.setValue(value);
                    textFields[col][row].setText(new CellValueConverter().toString(cell.getValue()));
                    setValidTextField(col, row);
                    helpAnimators[col][row].Blink();
                }
                else{
                    helpButton.setDisable(true);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ayuda");
                    alert.setHeaderText(null);
                    alert.setContentText("No hay más ayudas válidas :C");
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
                updateTextFields();
            }

            });

        newBoardButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                board.clear();
                board.fillBoard();
                //board.testBoard();
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

    public void setInvalidTextField(int col, int row) {
        //setTextFieldBorder(textField, "lightcoral");
        errorAnimators[col][row].startBlinking();
        textFields[col][row].setTooltip(new Tooltip("Número inválido en fila, columna o bloque"));
    }

    public void setValidTextField(int col, int row) {
        errorAnimators[col][row].stopBlinking();
        //setTextFieldBorder(textField, "transparent");
        textFields[col][row].setTooltip(null);
        textFields[col][row].setStyle(textFieldBaseStyle);
    }

    public void setInitialTextField(int col, int row) {
        textFields[col][row].setStyle("-fx-font-size: 20; -fx-font-family: 'Arial';  -fx-text-fill: '624E88'; -fx-opacity: 1.0; -fx-font-weight: bold; -fx-border-color: 'transparent'; -fx-border-width: 1.5;");
        textFields[col][row].setEditable(false);
    }

    public void setErrorMessage(String message) {
        errorLabel.setText(message);
        fadeInOut(errorLabel);

    }

    public void fadeInOut(Node node) {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.5), node);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setCycleCount(1);
        fadeIn.play();

        PauseTransition delay = new PauseTransition(Duration.millis(750));
        delay.setOnFinished(event -> {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), node);
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setCycleCount(1);
            fadeOut.play();
        });
        delay.play();

    }



}

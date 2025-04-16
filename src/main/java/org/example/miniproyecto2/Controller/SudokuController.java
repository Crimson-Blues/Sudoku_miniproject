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

/**
 * Controller class for the main Sudoku game view.
 * Handles user interaction, validation, and UI updates for the Sudoku board.
 */
public class SudokuController {
    /**
     * The {@link javafx.scene.layout.GridPane} that visually represents the Sudoku board.
     */
    @FXML
    private GridPane boardGridPane;
    /**
     * The {@link javafx.scene.control.Button} that provides the player with a hint.
     */
    @FXML
    private Button helpButton;
    /**
     * The main container pane for the scene, an {@link javafx.scene.layout.AnchorPane}.
     */
    @FXML
    private AnchorPane mainPane;
    /**
     * The {@link javafx.scene.control.Button} that restarts the current game board.
     */
    @FXML
    private Button restartButton;
    /**
     * The {@link javafx.scene.control.Button} that generates a new Sudoku board.
     */
    @FXML
    private Button newBoardButton;
    /**
     * A {@link javafx.scene.control.Label} used to display error messages to the player.
     */
    @FXML
    private Label errorLabel;
    /**
     * The size of the Sudoku grid (6x6 in this implementation).
     */
    private static final int GRID_SIZE = 6;
    /**
     * 2D array of TextFields representing the visual cells on the Sudoku board.
     */
    private TextField[][] textFields = new TextField[GRID_SIZE][GRID_SIZE];
    /**
     * 2D array of CellErrorAnimator used to animate invalid cell entries.
     */
    private CellErrorAnimator[][] errorAnimators = new CellErrorAnimator[GRID_SIZE][GRID_SIZE];
    /**
     * 2D array of CellHelpAnimator used to highlight hints provided to the user.
     */
    private CellHelpAnimator[][] helpAnimators = new CellHelpAnimator[GRID_SIZE][GRID_SIZE];
    /**
     * Logical representation of the Sudoku board, containing cell data and validation logic.
     */
    private Board board;
    /**
     * Stores the default style string applied to editable TextFields.
     */
    private String textFieldBaseStyle;

    /**
     * Initializes the controller and sets up the game board and button actions.
     */
    @FXML
    public void initialize() {
        //Creates and fills the board
        board = new Board(GRID_SIZE, GRID_SIZE);
        initializeTextFields();
        handleButtons();
        errorLabel.setText("");
    }

    /**
     * Fills the grid with {@link javafx.scene.control.TextField}s, sets up animators and styles.
     */
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


    /**
     * Creates a styled {@link javafx.scene.control.TextField} and attaches event listeners.
     *
     * @param col the column index of the cell
     * @param row the row index of the cell
     * @return a configured TextField
     */
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

    /**
     * Updates all the text fields from the board state.
     */
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

    /**
     * Binds actions to the help, restart and new board buttons.
     */
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
                    //helpButton.setDisable(true);
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
    /**
     * Sets a custom border color on the given {@link javafx.scene.control.TextField}.
     *
     * @param tf    the TextField to style
     * @param color the color name or code
     */
    public void setTextFieldBorder(TextField tf, String color) {
        String existingStyle = tf.getStyle();
        //Remove previous border styles
        String cleanedStyle = existingStyle.replaceAll("-fx-border-color: [^;]+;", "");
        tf.setStyle(cleanedStyle + "-fx-border-color: " + color + ";");
    }

    /**
     * Marks a cell as invalid and displays a red glow animation.
     *
     * @param col the column index
     * @param row the row index
     */
    public void setInvalidTextField(int col, int row) {
        //setTextFieldBorder(textField, "lightcoral");
        errorAnimators[col][row].startBlinking();
        textFields[col][row].setTooltip(new Tooltip("Número inválido en fila, columna o bloque"));
    }

    /**
     * Clears invalid state and resets the text field style.
     *
     * @param col the column index
     * @param row the row index
     */
    public void setValidTextField(int col, int row) {
        errorAnimators[col][row].stopBlinking();
        //setTextFieldBorder(textField, "transparent");
        textFields[col][row].setTooltip(null);
        textFields[col][row].setStyle(textFieldBaseStyle);
    }

    /**
     * Applies the initial bold style to a cell that was generated by the board.
     *
     * @param col the column index
     * @param row the row index
     */
    public void setInitialTextField(int col, int row) {
        textFields[col][row].setStyle("-fx-font-size: 20; -fx-font-family: 'Arial';  -fx-text-fill: '624E88'; -fx-opacity: 1.0; -fx-font-weight: bold; -fx-border-color: 'transparent'; -fx-border-width: 1.5;");
        textFields[col][row].setEditable(false);
    }

    /**
     * Displays an error message in the error label with fade-in and fade-out animation.
     *
     * @param message the message to display
     */
    public void setErrorMessage(String message) {
        errorLabel.setText(message);
        fadeInOut(errorLabel);

    }
    /**
     * Animates a {@link javafx.scene.Node} with fade-in and fade-out.
     *
     * @param node the node to animate
     */
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

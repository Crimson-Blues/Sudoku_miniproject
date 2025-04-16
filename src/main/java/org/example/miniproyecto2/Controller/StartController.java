package org.example.miniproyecto2.Controller;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for the start screen of the Sudoku game.
 * Handles the animation and interaction logic for the initial view.
 */
public class StartController {
    /**
     * The root {@link javafx.scene.layout.AnchorPane} of the start view.
     */
    @FXML
    private AnchorPane rootPane;
    /**
     * The {@link javafx.scene.control.Label} that acts as a "Start Game" button.
     */
    @FXML
    private Label startLabel;

    /**
     * Initializes the controller after the FXML is loaded.
     * Adds animations, hover effects, and a click handler to the start label.
     */
    @FXML
    public void initialize() {
        FadeTransition ft = new FadeTransition(Duration.seconds(1.2), startLabel);
        ft.setFromValue(1.0);
        ft.setToValue(0.3);
        ft.setCycleCount(Animation.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();

        startLabel.setOnMouseEntered(event -> {
            startLabel.setStyle("-fx-text-fill: #8A2BE2; " +
                            "-fx-effect: dropshadow(gaussian, rgba(138,43,226,0.7), 8, 0.5, 0, 0);" +
                            " -fx-font-weight: bold;"+
                            "-fx-cursor: hand;");

        });

        startLabel.setOnMouseExited(event -> {
            startLabel.setStyle("-fx-text-fill: black; " +
                            "-fx-cursor: hand;");

        });

        startLabel.setOnMouseClicked(this::handleStartClick);
    }

    /**
     * Handles the click event on the start label.
     * Loads the Sudoku game view and switches the scene.
     *
     * @param event The mouse click event.
     */
    private void handleStartClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/miniproyecto2/sudoku-view.fxml"));
            Scene sudokuScene = new Scene(loader.load());

            Stage stage = (Stage) startLabel.getScene().getWindow();
            stage.setScene(sudokuScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


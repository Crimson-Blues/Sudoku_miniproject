package org.example.miniproyecto2.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller class for the victory screen, which is displayed when the player wins the game.
 * It provides functionality to play the game again or exit the game.
 */
public class VictoryController {
    /**
     * The button that allows the player to play the game again.
     *
     * @see javafx.scene.control.Button
     */
    @FXML
    private Button playAgainButton;
    /**
     * The button that allows the player to exit the game.
     *
     * @see javafx.scene.control.Button
     */
    @FXML
    private Button exitButton;

    /**
     * Initializes the controller by setting the actions for the buttons.
     * The actions handle transitioning to the start screen for a new game or exiting the game entirely.
     */
    public void initialize() {
        playAgainButton.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/miniproyecto2/start-view.fxml"));
                Scene startScene = new Scene(loader.load());
                Stage stage = (Stage) playAgainButton.getScene().getWindow();
                stage.setScene(startScene);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        exitButton.setOnAction(e -> {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        });
    }
}

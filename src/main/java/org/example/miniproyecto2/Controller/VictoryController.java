package org.example.miniproyecto2.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class VictoryController {
    @FXML
    private Button playAgainButton;

    @FXML
    private Button exitButton;

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

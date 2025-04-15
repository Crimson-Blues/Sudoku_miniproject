package org.example.miniproyecto2.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuStage extends Stage {
    public SudokuStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/miniproyecto2/start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setTitle("Sudoku");
        setResizable(false);
        getIcons().add(new Image(String.valueOf(getClass().getResource("/org/example/miniproyecto2/images/favicon.png"))));
        setScene(scene);
        show();
    }
}

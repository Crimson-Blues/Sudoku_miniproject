package org.example.miniproyecto2.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuStage extends Stage {
    public SudokuStage() throws IOException {
        System.out.println(getClass().getResource("/org/example/miniproyecto2/sudoku-view.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/miniproyecto2/sudoku-view.fxml"));
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/org/example/miniproyecto2/images/fondo.png"));
        Scene scene = new Scene(fxmlLoader.load());
        setTitle("Sudoku");
        setResizable(false);
        setScene(scene);
        show();
    }
}

package org.example.miniproyecto2;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.miniproyecto2.View.SudokuStage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new SudokuStage();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

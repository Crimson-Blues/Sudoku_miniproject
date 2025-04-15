package org.example.miniproyecto2;

import javafx.application.Application;
import org.example.miniproyecto2.View.SudokuStage;

import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new SudokuStage();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

package org.example.miniproyecto2;

import javafx.application.Application;
import org.example.miniproyecto2.View.SudokuStage;

import javafx.stage.Stage;

import java.net.URL;

/**
 * Main class that serves as the entry point of the JavaFX Sudoku application.
 *
 * <p>This class launches the JavaFX application and initializes the main Sudoku window.</p>
 * @author J. Diego Cárdenas-2416437
 * @author Liseth Natalia Rivera-2223510
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application by creating and displaying the main Sudoku stage.
     *
     * @param primaryStage the primary stage provided by the JavaFX runtime (unused in this case)
     * @throws Exception if an error occurs during application start
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        new SudokuStage();
    }

    /**
     * Main method that launches the JavaFX application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }

}

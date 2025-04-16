package org.example.miniproyecto2.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the main window (stage) of the Sudoku application.
 *
 * <p>This class loads the initial FXML layout, sets up the application window properties,
 * and displays the Sudoku interface to the user.</p>
 */
public class SudokuStage extends Stage {
    /**
     * Constructs and initializes the main Sudoku stage.
     *
     * <p>This sets the title, icon, loads the FXML UI, and displays the window.</p>
     *
     * @throws IOException if the FXML file cannot be loaded
     */
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

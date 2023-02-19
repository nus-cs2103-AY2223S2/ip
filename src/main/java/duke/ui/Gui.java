package duke.ui;

import duke.controller.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * A class that acts as the entry point into the GUI.
 */
public class Gui extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create control
        MainWindow mainWindow = new MainWindow();

        // Set window
        configureStage(primaryStage);
        Scene scene = new Scene(mainWindow);
        primaryStage.setScene(scene); // Setting the stage to show our screen
        primaryStage.show(); // Render the stage.

    }

    private void configureStage(Stage primaryStage) {
        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);
    }
}

package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Graphical user interface app for Duke.
 */
public class DukeApp extends Application {

    /**
     * Starts the app.
     *
     * @param stage the primary stage for this application,
     *      onto which the application scene can be set.
     *      Applications may create other stages, if needed,
     *      but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }
}


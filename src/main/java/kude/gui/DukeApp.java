package kude.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main JavaFX Application for Kude
 */
public class DukeApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        var label = new Label("Hello world!");
        var scene = new Scene(label);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

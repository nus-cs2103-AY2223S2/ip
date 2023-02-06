package io;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Gui extends Application {
    @Override
    public void start(Stage stage) {
        Label hello = new Label("Hello");
        Scene scene = new Scene(hello);

        stage.setScene(scene);
        stage.show();
    }
}

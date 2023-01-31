package view.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Gui extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        primaryStage.setTitle("Membot");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

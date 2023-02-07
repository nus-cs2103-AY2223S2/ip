package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import duke.controller.MainWindow;

public class GuiApp extends Application {

    @Override
    public void start(Stage stage) {
        MainWindow window = new MainWindow();
        Scene scene = new Scene(window);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        System.out.println("Quit...");
    }
}

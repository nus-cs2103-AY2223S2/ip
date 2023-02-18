package duke;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import duke.io.input.ui.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow();
        stage.setScene(new Scene(mainWindow));
        stage.setTitle("Dr. Zola");
        stage.setMinWidth(398);
        stage.setMinHeight(600);
        stage.setResizable(false);
        stage.show();
        mainWindow.greet();
    }
}

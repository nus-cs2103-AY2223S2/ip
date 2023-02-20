package duke;

import duke.io.input.ui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow();
        stage.setScene(new Scene(mainWindow));
        stage.setTitle("DaBeztSithLord");
        stage.setMinWidth(800);
        stage.setMinHeight(800);
        stage.setResizable(true);
        stage.show();
        mainWindow.greet();
    }
}

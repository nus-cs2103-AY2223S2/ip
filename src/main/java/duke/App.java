package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.main.Storage;
import duke.ui.MainWindow;

public class App extends Application {
    private Duke instance;

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            instance = new Duke(Storage.loadFromDisk("data.dat"));
        } catch (ClassNotFoundException e) {
            new Alert(AlertType.ERROR, "Your data file was corrupted, so we were unable to read your tasks from there").show();
            instance = new Duke();
        }

        MainWindow controller = new MainWindow();
        VBox box = Utils.loadFxmlFile(getClass().getResource("/view/MainWindow.fxml"), controller);
        controller.setDuke(instance);

        Scene scene = new Scene(box);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

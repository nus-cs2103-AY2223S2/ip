package duke;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.controllers.DialogBox;
import duke.controllers.MainWindow;
import duke.task.Reminder;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("ip/data/tasks.txt");


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            assert duke != null: "duke should not be null";
            fxmlLoader.<MainWindow>getController().showWelcome();
            fxmlLoader.<MainWindow>getController().showReminders();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

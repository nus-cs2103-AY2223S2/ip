import duke.command.TaskList;
import duke.gui.Duke_GUI;
import duke.gui.MainWindow;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.ToDo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

/**
 * The type Duke.
 */
public class Duke extends Application {

    /**
     * The Scanner Class.
     */
    static Scanner sc = new Scanner(System.in);
    private final Duke_GUI UI = new Duke_GUI();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        TaskList manager = new TaskList("/Users/s.f/ip/src/Data/duke.txt");
        Application.launch(Duke_GUI.class, args);

    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(UI);
            stage.show();
            stage.setOnCloseRequest(event -> {
                Platform.exit();
                System.exit(0);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


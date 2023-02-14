package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import duke.main.Storage;
import duke.task.Task;
import duke.ui.MainWindow;
import duke.ui.UI;

public class App extends Application {
    private static App singleton;

    public static void sendCloseRequest() {
        singleton.primaryStage.fireEvent(new WindowEvent(
            singleton.primaryStage, 
            WindowEvent.WINDOW_CLOSE_REQUEST
        ));
    }

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        List<Task> tasks;
        while (true) {
            try {
                tasks = Storage.loadFromDisk("data.dat");
                break;
            } catch (Exception e) {
                ButtonType res = UI.showRetryDialog(AlertType.ERROR, "Failed to load your saved tasks! Try again?");
                if (res == ButtonType.NO) {
                    tasks = new ArrayList<>();
                    break;
                }
            }
        }

        Duke instance = new Duke(tasks);
        MainWindow controller = new MainWindow();
        VBox box = Utils.loadFxmlFile(getClass().getResource("/view/MainWindow.fxml"), controller);
        controller.setDuke(instance);

        Scene scene = new Scene(box);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        this.primaryStage = primaryStage;
        scene.getWindow()
            .addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
                while (true) {
                    try {
                        Storage.saveToDisk("data.dat", instance.getTaskList());
                        return;
                    } catch (IOException ex) {
                        ButtonType res = UI.showRetryDialog(
                            AlertType.ERROR, 
                            String.format("Failed to save your tasks: %s Try again?", ex.getMessage()
                        ));
                        if (res == ButtonType.NO) {
                            break;
                        }
                    }
                }
            });

        singleton = this;
    }
}

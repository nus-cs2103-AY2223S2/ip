package duke;

import duke.common.Storage;
import duke.controller.MainWindow;
import duke.model.Model;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DukeGui extends Application {

    private Model model;
    private MainWindow window;

    @Override
    public void init() {
        model = new Model(Storage.readTaskList());
        window = new MainWindow(model);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(window);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        System.out.println("Quit...");
        Storage.writeTaskList(model.getTaskList());
    }
}

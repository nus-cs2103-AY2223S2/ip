package duke;

import duke.gui.Ui;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {
    private static Storage storage = new Storage();
    private static TaskList tasks = new TaskList();
    private static Ui ui = new Ui();
    private static Parser parser = new Parser();

//    public static void main(String[] args) {
//        ui.welcomeMessage();
//        parser.getInput();
//    }

    @Override
    public void start(Stage stage) {
        // Creating a new Label control
        Label helloWorld = new Label("Hello World!");

        // Setting the scene to be our Label
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }
}

package duke;

import duke.command.Command;
import duke.task.TaskList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//public class Duke extends Application {
public class Duke {

        private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filepath) {
        try {
            this.storage = new Storage(filepath);
            this.taskList = new TaskList(storage.loadTasks());
            this.ui = new Ui();
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        }
    }
//    @Override
//    public void start(Stage stage) {
//        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
//        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
//
//        stage.setScene(scene); // Setting the stage to show our screen
//        stage.show(); // Render the stage.
//    }
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String inputCommandString = ui.readCommand();
                ui.showLine();
                Command inputCommand = Parser.parse(inputCommandString);
                inputCommand.execute(taskList, ui, storage);
                isExit = inputCommand.isExit();
                ui.showLine();
            } catch (DukeException err) {
                ui.showMessage(err.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}

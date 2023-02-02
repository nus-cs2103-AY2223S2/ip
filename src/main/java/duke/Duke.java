package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import duke.exceptions.DukeException;
import duke.exceptions.MemoryFailedException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Represents the entry point for the Duke application. The main function resides in this class.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList allTasks;

    public Duke(String[] memoryPathArray) {
        this.storage = new Storage(memoryPathArray);
        this.allTasks = new TaskList();
        try {
            this.storage.loadTasks(this.allTasks);
        } catch (MemoryFailedException e) {
            Ui.println(e.toString());
        }
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        Ui.printOnStartup();
        boolean promptAgain = true;
        while (promptAgain) {
            Ui.printPrompt();
            String command = Ui.listen();
            try {
                promptAgain = Parser.handleCommands(command, this.allTasks);
            } catch (DukeException e) {
                Ui.println(e.toString());
            }
            Ui.printDottedLine();
        }
        this.storage.saveTasks(this.allTasks);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Acts as the entry point for the Duke application.
     */
    public static void main(String[] args) {
        String[] memoryPathArray = {".", "memory.txt"};
        new Duke(memoryPathArray).run();
    }
}

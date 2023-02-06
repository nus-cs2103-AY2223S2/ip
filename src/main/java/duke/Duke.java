package duke;

import duke.command.Command;
import duke.exception.CommandException;
import duke.exception.DukeException;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main class for Duke chatbot
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initializes the Duke chatbot.
     *
     * @param filePath Specifies path to save and retrieve content.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main function
     * @param args input from the command line (ignored)
     */
    public static void main(String[] args) {
        new Duke(".\\tasks.txt").run();
    }

    /**
     * Initialise the launcher
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Method that abstracts the running of the program
     */
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String input = this.ui.readCommand();
            ui.showLine();
            try {
                Command command = Parser.parse(input);
                command.execute(this.tasks, this.storage, this.ui);
                isExit = command.isExit();
            } catch (CommandException commandException) {
                ui.showCommandError(input, commandException);
            } catch (DukeException dukeException) {
                System.out.println(dukeException);
            } finally {
                ui.showLine();
            }
        }
    }
}

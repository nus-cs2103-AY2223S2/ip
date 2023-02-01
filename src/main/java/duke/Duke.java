package duke;
import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Main logic of Duke the chat bot which tracks the user's tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke object.
     *
     * @param filePath Path of the file where the task objects are stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the chat flow.
     * Prints the welcome message when user first starts up the chat.
     * Loops while getting user input for commands until user inputs "bye".
     * Exits chat and prints farewell message.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method which runs the duke object.
     */
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}

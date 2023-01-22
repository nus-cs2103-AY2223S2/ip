package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import command.Command;
import task.TaskList;

/**
 * The Duke ChatBot.
 */
public class Duke {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor for Duke.
     * @param filePath The path of the local file where tasks are stored from the previous session.
     */
    public Duke(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Begins the chat session with the user
     */
    public void run() {
        /* Greet the user */
        ui.showLine();
        ui.showWelcome();
        ui.showLine();

        boolean isExit = false;

        while (!isExit) {
            try {
                String userCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(userCommand);
                c.execute(this.taskList, this.ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.close();

        try {
            this.storage.save(this.taskList);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke(Paths.get(".", "data", "duke.txt")).run();
    }
}

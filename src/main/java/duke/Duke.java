package duke;

import duke.command.Command;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * A constructor for usage in GUI.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data.txt", this.ui);
        this.tasks = new TaskList(storage.loadData());
    }
    /**
     * Constructor for Duke.
     * @param filePath Path of data file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, this.ui);
        this.tasks = new TaskList(storage.loadData());
    }
    /**
     * Runs the Duke program.
     */
    public void run() {
        Parser parser = new Parser(this.storage, this.tasks, this.ui);
        ui.displayWelcomeMessage();
        while (ui.isRunning()) {
            try {
                String[] userInput = ui.readUserInput();
                Command command = parser.parse(userInput);
                command.execute(storage, tasks, ui);
            } catch (Exception e) {
                ui.displayMessage(e.getMessage());
            }
        }
    }
    /**
     * Gets a response based on user's input.
     * @param input The user's input.
     * @return A String response.
     * @throws DukeException If the user input is invalid.
     */
    public String getResponse(String input) throws DukeException {
        Parser parser = new Parser(this.storage, this.tasks, this.ui);
        String[] userInput = input.split(" ", 2);
        try {
            Command command = parser.parse(userInput);
            return command.execute(storage, tasks, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke("./data.txt").run();
    }
}

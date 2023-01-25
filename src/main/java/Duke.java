import commands.Command;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import tasks.*;
import ui.Ui;

/**
 * Duke class that runs main chatbot logic.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates Duke object and populate field values from a file if it exists,
     * otherwise create Duke object with default field values.
     *
     * @param filePath Path of the file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main logic of Duke.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                ((Command) c).execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point to the application.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}

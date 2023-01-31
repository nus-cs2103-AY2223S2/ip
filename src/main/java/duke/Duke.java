package duke;

import duke.command.Command;

/**
 * Duke is a personal assistant chatbot that helps a person to keep track of various things.
 * 
 * @author Tan Yu Fei
 * @version 0.1
 * @since 2022-01-25
 */
public class Duke {
    /** The storage object that handles the loading and saving of tasks. */
    private Storage storage;
    /** The task list that contains the tasks. */
    private TaskList tasks;
    /** The ui object that handles the user interface. */
    private Ui ui;

    /**
     * Constructor for Duke.
     * 
     * @param filePath the path of the file that contains the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}

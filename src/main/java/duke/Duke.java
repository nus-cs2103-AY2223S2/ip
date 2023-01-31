package duke;

import duke.command.Command;

/**
 * Represents a duke chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns duke object.
     * @param filePath path for backup file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.readArray();
        } catch (DukeException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the duke chatbot.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                //ui.showError(e.getMessage());
            } finally {
                //ui.showLine();
            }
        }
    }

    /**
     * Starts the duke chatbot.
     * @param args input arguments.
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}

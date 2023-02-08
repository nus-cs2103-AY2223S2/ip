package duke;

import duke.command.Command;
import duke.ui.Ui;

/**
 * Duke task management
 */
public class Duke {
    public static final String DEFAULT_FILEPATH = "data/tasks.txt";

    private Storage storage = new Storage(DEFAULT_FILEPATH);
    private TaskList tasks = new TaskList();
    private final Ui ui = new Ui();

    /**
     * Generates a Duke object.
     * Stores saved data in specified filepath.
     *
     * @param args Filepath, UI type.
     */
    public Duke(String... args) {
        if (args.length > 0) {
            storage = new Storage(args[0]);
        }
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Creates and run Duke.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Executes Duke.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                String response = c.execute(tasks, ui, storage);
                ui.echo(response);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String getWelcome() {
        return ui.getWelcomeMessage();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

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
     * @param filepath Filepath of storage.
     */
    public Duke(String... filepath) {
        if (filepath.length > 0) {
            assert filepath.length == 1 : "More than 1 filepath provided";
            storage = new Storage(filepath[0]);
        }
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            assert tasks.size() == 0 : "TaskList started with undefined task";
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
                assert fullCommand != null : "User's command is null";
                Command c = Parser.parse(fullCommand);
                String response = c.execute(tasks, ui, storage);
                assert response != null : "Response is null";
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
            String response = c.execute(tasks, ui, storage);
            assert response != null : "Response is null";
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

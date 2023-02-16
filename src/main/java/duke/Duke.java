package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke task management
 */
public class Duke {
    public static final String DEFAULT_FILEPATH = "data/tasks.txt";

    private Storage storage = new Storage(DEFAULT_FILEPATH);
    private TaskList tasks;
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
            storage.setLoadError(true);
            tasks = new TaskList();
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

        if (storage.isLoadError()) {
            ui.echo(ui.getLoadingErrorMessage());
        }

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();

                assert fullCommand != null : "User's command is null";

                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                String response = c.getResponse();

                assert response != null : "Response is null";

                ui.echo(response);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError(DukeException
                        .getError()
                        .getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String getWelcome() {
        return ui.getWelcomeMessage();
    }

    public String getLoadErrorMessage() {
        if (storage.isLoadError()) {
            return ui.getLoadingErrorMessage();
        }
        return null;
    }

    public String getResponse(String input) throws DukeException {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            String response = c.getResponse();

            assert response != null : "Response is null";

            return response;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (Exception e) {
            return DukeException
                    .getError()
                    .getMessage();
        }
    }
}

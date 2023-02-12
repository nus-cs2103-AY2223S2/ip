package duke;

/**
 * Encapsulates a GUI Application which acts as a Task Manager.
 *
 * @author Sean Chin Jun Kai
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor to initialise the Task Manager.
     *
     * @param filePath the path of the file where we want to store the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showErrorMessage(e);
            tasks = new TaskList();
        }
    }

    /**
     * Returns the result string to be shown to user in GUI
     * based on user's input.
     *
     * @param input text that user enters into GUI.
     * @return string to be displayed to user.
     */

    public String getResponse(String input) {
        try {
            Command command = Parser.parseUserResponse(ui, tasks, storage, input);
            return command.execute();
        } catch (DukeException e) {
            return ui.showErrorMessage(e);
        }
    }
}



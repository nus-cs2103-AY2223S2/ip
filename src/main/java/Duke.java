import response.Response;

import sys.Storage;
import sys.Ui;

import task.TaskList;

/**
 * Represents the whole Duke program.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialise the Duke backend components.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("tasks.txt");
        this.tasks = storage.load();
        this.ui.setContext(storage, tasks);
    }

    /**
     * Receives input to send to the UI.
     * @param input Input string send by the user.
     * @return Response from the UI.
     */
    public Response getResponse(String input) {
        return ui.send(input);
    }
}
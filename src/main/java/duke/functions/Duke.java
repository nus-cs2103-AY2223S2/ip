package duke.functions;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class Duke {
    protected static final String FILEPATH = "./data/duke.txt";
    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;
    public Duke() {
        ui = new Ui();
        assert FILEPATH != null;
        storage = new Storage(FILEPATH);
        try {
            assert tasks != null;
            storage.readDatabase(tasks);
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        TaskList list = new TaskList();
        d.storage.readDatabase(list);
        d.ui.run(list);
    }

    /**
     * Process the input and return the server response.
     * @param input The command from the user.
     * @return The server response delivered to the UI.
     */
    public String getResponse(String input) {
        try {
            Duke d = new Duke();
            Parser p = new Parser(d.tasks);
            String output = p.handleInput(input);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}

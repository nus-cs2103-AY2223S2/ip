package duke;

import java.io.IOException;
import duke.command.Command;

public class Duke  {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Generates and returns a response to user input.
     *
     * @param input command from user.
     * @return response from executed command.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String output = command.execute(tasks, storage, ui);
            return output;
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

    /**
     * Loads the list of tasks stored in the text file given by 'filepath'.
     *
     * @param filePath the path of the stored text file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (IOException e) {
            tasks = new TaskList();
            ui.showLoadingError();
        }
    }
}
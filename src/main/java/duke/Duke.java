package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;


public class Duke {

    private Command preCommand;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private boolean isExit = false;

    /**
     * Initialises the object
     */
    public Duke(String filePath) {
        preCommand = null;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input, preCommand);
            isExit = c.isExit();
            if (isExit) {
                String dukeText = "bye";
                return dukeText;
            }
            this.preCommand = c;
            return (c.execute(tasks, ui, storage));
        } catch (IllegalArgumentException e) {
            return Ui.showError("wrong");
        } catch (IOException e) {
            return Ui.showError(e.getMessage());
        } catch (DukeException e) {
            return Ui.showError(e.toString());
        }
    }


}

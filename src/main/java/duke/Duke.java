package duke;


import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main Duke class which keep track a list of tasks

 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;



    /**
     * Duke constructor that creates a new list with the specified input file directory
     */

    public Duke() {
        ui = new Ui();
        storage = new Storage("");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main Program that reads the commands and processes them onto DUKE
     *
     * @param input A input of String which is the command given by the user
     *
     * @return A string type of response of command
     */
    public String run(String input) {
        assert(input.length() > 0);
        try {
            String fullCommand = input;
            //ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse(fullCommand);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }

    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     *
     * @param input A string input to get response from the command
     *
     * @return a String denoting the response of command
     */
    public String getResponse(String input) {
        return this.run(input);
    }


}

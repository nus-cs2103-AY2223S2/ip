// Reference from the given code provided on CS2103 module website
package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Duke Class that runs the whole chatbot.
 */
public class Duke {

    private String res;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructor for Duke.
     *
     * @param filePath the path fo the file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            res = "";
            tasks = storage.load();
        } catch (DukeException e) {
            res = e.getMessage();
            tasks = new TaskList();
        }
    }


    /**
     * Function to get a response from the duke chatbot.
     *
     * @param fullCommand the command given by the user.
     * @return String the result of the command.
     */
    public String getResponse(String fullCommand) {
        try {
            Command.isValidCommand(fullCommand);
            Command c = Parser.parse(fullCommand);
            this.res = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            this.res = e.getMessage();
        } catch (IllegalArgumentException e) {
            this.res = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        } finally {
            storage.updateStorage(tasks);
        }
        return this.res;
    }
}

package duke;

import commands.Command;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * This class is the main of the program that allows user to track upcoming tasks
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs new instance of Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
    }

    /**
     * Greets User
     *
     * @return greetings
     */
    public String greetUser() {
        return ui.greetUser();
    }

    /**
     * Loads previous task list from local data base into application
     *
     * @return message to let user know if the laoding was successful
     */
    public String loadList() {
        try {
            this.tasks = new TaskList(storage.load());
            return ui.notifySuccessfulLoad();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Generates Duke's response to a given user command
     *
     * @param input user command
     * @return Duke's response
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return this.ui.printException(e);
        }
    }
}

package duke.main;

import java.util.ArrayList;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Duke is a class that reacts to user's input
 * It uses other classes like Command, Parser, Storage, and TaskList to perform
 * various operations depending on the user's input
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Command command;
    /**
     * Constructor. Takes in a filePath to create Storage object
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        command = new Command();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Get message based on user input
     */
    public String getResponse(String input) throws DukeException {
        try {
            return this.command.executeCommand(this.parser.getCommand(input), input, tasks, storage);
        } catch (Exception e) {
            return ui.getUnknownMessage();
        }
    }
}

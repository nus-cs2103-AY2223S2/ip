package alfred.main;

import java.util.ArrayList;

import alfred.command.Command;
import alfred.exception.AlfredException;
import alfred.parser.Parser;
import alfred.storage.Storage;
import alfred.tasklist.TaskList;
import alfred.ui.Ui;

/**
 * Alfred is a class that reacts to user's input
 * It uses other classes like Command, Parser, Storage, and TaskList to perform
 * various operations depending on the user's input
 */
public class Alfred {
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
    public Alfred(String filePath) {
        ui = new Ui();
        parser = new Parser();
        command = new Command();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (AlfredException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Get message based on user input
     */
    public String getResponse(String input) {
        try {
            return this.command.executeCommand(this.parser.getCommand(input), input, tasks, storage);
        } catch (Exception e) {
            return ui.getUnknownMessage();
        }
    }
}

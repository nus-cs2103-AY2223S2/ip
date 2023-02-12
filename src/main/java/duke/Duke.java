package duke;

import duke.commands.Command;
import duke.exceptions.InvalidCommandException;

/**
 * Class to initialise the Thesh Bot.
 */
public class Duke {

    private Storage storage;
    private static TaskList listOfTasks = new TaskList();
    private Ui ui;
    private Parser parser;
    private boolean isExit;

    public Duke() {
        this.storage = new Storage();
        try {
            listOfTasks = storage.loadFile();
        } catch (InvalidCommandException e) {
            ui.showCreateNewFile();
            listOfTasks = new TaskList();
        }
        this.ui = new Ui();
        this.parser = new Parser(listOfTasks);
        this.isExit = false;

    }

    public Ui getUi() {
        return this.ui;
    }

    public boolean getIsExit() {
        return this.isExit;
    }

    public void exitDuke() {
        this.isExit = true;
    }

    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            String response = c.handleCommand();
            this.isExit = c.isExit();
            return response;
        } catch (InvalidCommandException e) {
            return e.getMessage();
        } finally {
            storage.writeToFile(listOfTasks);
        }
    }

}


package duke;

import command.Command;
import dukeexception.DukeException;
import gui.Gui;
import parser.Parser;
import storage.Storage;
import task.TaskList;

/**
 * Duke chatbot that will respond to users' commands.
 */
public class Duke {
    private final Gui gui;
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Duke.
     */
    public Duke(Gui gui) {
        this.storage = new Storage("./data/taskList.txt");
        this.gui = gui;
        gui.greet();
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.storage.create();
            gui.showError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Gets the response based on the user input.
     * @param input The user input.
     */
    public boolean getResponse(String input) {
        try {
            this.gui.echo(input);
            Command c = Parser.parse(input);
            c.execute(this.taskList, this.gui, this.storage);
            return c.isExit();
        } catch (DukeException e) {
            this.gui.showError(e.getMessage());
            return false;
        }
    }
}

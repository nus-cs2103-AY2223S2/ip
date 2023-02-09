package duke;

import java.io.FileNotFoundException;

import duke.command.Command;
import duke.command.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke program implements an interactive chatbot with the
 * function to keep track of tasks.
 *
 * @author      Oskar Lew
 * @version     0.1
 * @since       0.1
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private final TaskList tasks;
    private final Parser parser;
    /**
     * Constructor for class Duke.
     *
     * @param  fileName Filename of saved task list.
     */
    public Duke(String fileName) {
        this.storage = new Storage(fileName);
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.parser = new Parser();
    }

    /**
     * Method that returns the opening message.
     * @return  String of opening message.
     */
    public String getOpeningMessage() {
        return ui.openingMsg();
    }

    /**
     * Method to get response from duke.
     * @param input String input by the user.
     * @return String response by duke.
     */
    public String getResponse(String input) {
        Command c = parser.parse(input);
        return c.execute(tasks, ui, storage);
    }

    /**
     * Load stored task into Duke.
     */
    public void loadTasks() {
        try {
            storage.loadToDuke(this.tasks, this.ui, this.storage);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}

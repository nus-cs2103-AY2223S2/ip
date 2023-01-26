package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

import java.util.LinkedList;

/**
 * Duke program implements an interactive chatbot with the
 * function to keep track of tasks.
 *
 * @author      Oskar Lew
 * @version     0.1
 * @since       0.1
 */
public class Duke {
    Storage storage;
    Ui ui;
    TaskList tasks;


    /**
     * Constructor for class Duke.
     *
     * @param  fileName Filename of saved task list.
     */
    public Duke(String fileName) {
        this.storage = new Storage(fileName);
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

    /**
     * This method is used to start the chat.
     *
     */
    private void chat() {
        this.ui.greetings();
        Parser p = new Parser();
        boolean isExit = false;
        while (!isExit) {
            String command = this.ui.getLine();
            Command c = p.parse(command);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        }
        ui.endUi();
    }

    /**
     * This is the main method that starts the Duke program.
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        new Duke("duke").chat();
    }
}

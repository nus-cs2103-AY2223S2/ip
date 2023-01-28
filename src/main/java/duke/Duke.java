package duke;

import duke.exceptions.DukeException;
import duke.exceptions.MemoryFailedException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Represents the entry point for the Duke application. The main function resides in this class.
 */
public class Duke {

    private Storage storage;
    private TaskList allTasks;

    public Duke(String[] memoryPathArray) {
        this.storage = new Storage(memoryPathArray);
        this.allTasks = new TaskList();
        try {
            this.storage.loadTasks(this.allTasks);
        } catch (MemoryFailedException e) {
            Ui.println(e.toString());
        }
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        Ui.printOnStartup();
        boolean promptAgain = true;
        while (promptAgain) {
            Ui.printPrompt();
            String command = Ui.listen();
            try {
                promptAgain = Parser.handleCommands(command, this.allTasks);
            } catch (DukeException e) {
                Ui.println(e.toString());
            }
            Ui.printDottedLine();
        }
        this.storage.saveTasks(this.allTasks);
    }

    /**
     * Acts as the entry point for the Duke application.
     */
    public static void main(String[] args) {
        String[] memoryPathArray = {".", "memory.txt"};
        new Duke(memoryPathArray).run();
    }
}

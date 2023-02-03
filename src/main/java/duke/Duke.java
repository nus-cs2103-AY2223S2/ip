package duke;

import duke.exceptions.DukeException;
import duke.exceptions.MemoryFailedException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Represents the entry point for the Duke application. The main function resides in this class.
 */
public class Duke {

    private Storage storage;
    private TaskList allTasks;
    private static String[] defaultMemoryPathArray = {".", "memory.txt"};

    public Duke() {
        this(defaultMemoryPathArray);
    }

    public Duke(String[] memoryPathArray) {
        this.storage = new Storage(memoryPathArray);
        this.allTasks = new TaskList();
        try {
            this.storage.loadTasks(this.allTasks);
        } catch (MemoryFailedException e) {
            // TODO: Handle this error in a user-friendly manner
            e.printStackTrace();
        }
    }

    /**
     * Takes in a command and outputs the appropriate response.
     */
    public String getResponse(String command) {
        String response = "There was an error with your query!";
        try {
            response = Parser.handleCommands(command, this.allTasks);
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
        return response;
    }

    public void saveTasks() {
        this.storage.saveTasks(this.allTasks);
    }

}

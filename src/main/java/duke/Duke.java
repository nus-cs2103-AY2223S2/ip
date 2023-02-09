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

    private static String[] defaultMemoryPathArray = {".", "memory.txt"};

    private Storage storage;
    private TaskList allTasks;

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
        String response = "";
        try {
            response = Parser.handleCommands(command, this.allTasks);
            assert response != null : "Response from parser should not be null!";
        } catch (DukeException e) {
            response = e.toString();
        }
        return response;
    }

    public void saveTasks() {
        this.storage.saveTasks(this.allTasks);
    }

}

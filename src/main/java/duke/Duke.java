package duke;

import java.io.IOException;

/**
 * Represents Main class
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs an instance
     * @param filePath file path for saving data
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList();

        try {
            tasks = storage.load();
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Gets a response to the user command
     * @param input user command
     * @return response to the user command
     */
    public String getResponse(String input) {
        String temp;

        try {
            temp = Parser.parse(input, tasks);
            storage.updateFile(tasks);
            return temp;
        } catch (IOException e) {
            return "Error occurs!";
        }
    }
}

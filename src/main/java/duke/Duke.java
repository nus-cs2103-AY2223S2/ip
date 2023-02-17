package duke;

import java.io.IOException;

import duke.tasks.TaskList;

/**
 * Main class where duke is initialized and runs.
 */
public class Duke {
    private TaskList tasks;

    /**
     * Constructor method. Instantiates and loads a saved task list from a data file.
     */
    public Duke() {
        tasks = new TaskList();
        try {
            Storage.loadFromFile(tasks);
        } catch (IOException e) {
            // No file found
            e.printStackTrace();
        }
    }

    /**
     * Gets the task list.
     *
     * @return task list
     */
    public TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Evaluate if command execution was successful.
     *
     * @param response command input from user
     * @return whether command execution was succesful
     */
    public boolean isSuccessfulExecution(String response) {
        return response.startsWith("I couldn't do what you asked for.");
    }
}


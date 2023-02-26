package duke;

import java.io.IOException;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks TaskList object containing list of tasks.
     * @param response Response object for dealing with user interactions.
     * @param storage Storage object to save tasks to a file.
     * @throws IOException If I/O error occurs.
     */
    public abstract String execute(TaskList tasks, Response response, Storage storage) throws IOException;
}

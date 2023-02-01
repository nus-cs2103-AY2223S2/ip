package duke;

import java.io.IOException;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks TaskList object containing list of tasks.
     * @param ui Ui object for dealing with user interactions.
     * @param storage Storage object to save tasks to a file.
     * @throws IOException If I/O error occurs.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}

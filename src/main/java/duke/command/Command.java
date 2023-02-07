package duke.command;

import duke.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public abstract class Command {
    /**
     * Represents the abstract Command class which serves as a base template to handle all possible commands by Duke.
     */
    private boolean isTerminated = false;

    /**
     * Checks if status of Duke is to be terminated.
     * @return true if an exit command is passed, false otherwise.
     */
    public boolean isTerminated() {
        return isTerminated;
    }

    /**
     * Sets Duke's termination status to true.
     */
    public void terminate() {
        this.isTerminated = true;
    }

    /**
     *
     * @param tasks a list of tasks.
     * @param ui Ui class to handle display messages.
     * @param storage Storage to handle saving/loading of data to/from the list of task.
     */
    public abstract void initCommand(TaskList tasks, Ui ui, Storage storage);
}



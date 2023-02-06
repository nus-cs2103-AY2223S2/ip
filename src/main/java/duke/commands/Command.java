package duke.commands;

import duke.Storage;
import duke.TaskException;
import duke.TaskList;

/**
 * Represents an abstract command
 */
public abstract class Command {
    protected String listAll(TaskList tl) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tl.getList().size(); i++) {
            str.append(String.format("%d: %s\n", i + 1, tl.getList().get(i)));
        }
        return str.toString();
    }

    /**
     * Executes the command
     */

    public abstract String execute(TaskList tasks, Storage storage) throws TaskException;

    /**
     * Returns whether this command represents an exit command
     */
    public boolean isExit() {
        return false;
    }
}

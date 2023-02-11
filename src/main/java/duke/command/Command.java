package duke.command;

import duke.exception.DukeException;

import duke.tasklist.TaskList;

/**
 * Represents a executable command.
 */
public abstract class Command {

    private final boolean isExit;

    protected Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExitCommand() {
        return isExit;
    }

    /**
     * Executes the command.
     *
     * @param taskList The TaskList object that manages the list of Tasks.
     * @return The String response of the chatbot.
     * @throws DukeException If anything goes wrong
     */
    public abstract String execute(TaskList taskList) throws DukeException;

}

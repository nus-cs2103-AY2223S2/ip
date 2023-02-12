package duke.command;

import duke.taskstorage.TaskList;

/**
 * Abstract class for Command.
 */
public abstract class Command {
    protected String userInput;

    /**
     * Constructor for Command class.
     * @param userInput
     */
    public Command(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes user command.
     * @param tasks Current TaskList.
     */
    public abstract String execute(TaskList tasks);
}

package duke.Commands;

import duke.TaskList;

/**
 * This class is an abstract class representing all commands
 */
public abstract class Command {
    // message is the command input
    private final String message;

    public Command(String message) {
        this.message = message;
    }

    /**
     * Executes this command
     *
     * @param toDoList The task list to be edited
     */
    abstract public void execute(TaskList toDoList);
}

package brotherbot.commands;

import brotherbot.storage.TaskList;

/**
 * Represents a command input by user.
 */
public abstract class Command {
    protected String input;

    /**
     * Constructor to create a Command object.
     *
     * @param input Input string with task information required for command execution.
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes command.
     *
     * @param storage Existing TaskList object required for command execution.
     */
    public abstract String execute(TaskList storage);
}

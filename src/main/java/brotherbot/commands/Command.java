package brotherbot.commands;

import brotherbot.storage.TaskList;
import brotherbot.ui.Ui;

/**
 * Represents a command input by user.
 */
public abstract class Command {
    protected String input;
    public boolean isExit = false;

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
     * @param ui Ui object required for command execution.
     */
    public abstract void execute(TaskList storage, Ui ui);
}

package cluck.commands;

import cluck.messages.Messages;
import cluck.tasklist.TaskList;

/**
 * Exit command when executed will stop the Cluck instance from running.
 */
public class ExitCommand implements Command {
    public String execute(TaskList taskList) {
        return Messages.MESSAGE_GOODBYE;
    }

    /**
     * Returns true if command is an instance of ExitCommand, false otherwise.
     *
     * @param command the command
     * @return the boolean
     */
    public static boolean equals(Command command) {
        return command instanceof ExitCommand;
    }
}

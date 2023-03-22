package cluck.commands;

import cluck.messages.Messages;
import cluck.storage.Storage;
import cluck.tasklist.TaskList;

/**
 * Invalid command is created when user does not give a known command.
 * It simply tells the user that their command given was invalid.
 */
public class InvalidCommand implements Command {
    private final String invalidCommand;

    /**
     * Instantiates a new Invalid command.
     *
     * @param invalidCommand The invalid command input by the user.
     */
    public InvalidCommand(String invalidCommand) {
        this.invalidCommand = invalidCommand;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Messages.MESSAGE_INVALID_COMMAND + "\n"
                + "You typed: " + invalidCommand;
    }
}

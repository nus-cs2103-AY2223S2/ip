package cluck.commands;

import cluck.messages.Messages;
import cluck.taskList.TaskList;

public class InvalidCommand implements Command {
    private final String invalidCommand;

    public InvalidCommand(String invalidCommand) {
        this.invalidCommand = invalidCommand;
    }

    @Override
    public String execute(TaskList taskList) {
        return Messages.MESSAGE_INVALID_COMMAND + "\n" + invalidCommand;
    }
}

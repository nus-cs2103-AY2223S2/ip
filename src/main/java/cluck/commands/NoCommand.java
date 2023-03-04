package cluck.commands;

import cluck.messages.Messages;
import cluck.tasklist.TaskList;

/**
 * No command is separate from invalid command, rather it is a place-holder command for when the user did not give any
 * input in the command line.
 */
public class NoCommand implements Command {
    public NoCommand() {};
    @Override
    public String execute(TaskList taskList) {
        return Messages.MESSAGE_NO_COMMAND_GIVEN;
    }
}

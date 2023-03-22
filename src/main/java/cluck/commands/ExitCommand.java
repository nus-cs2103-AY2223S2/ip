package cluck.commands;

import cluck.messages.Messages;
import cluck.storage.Storage;
import cluck.tasklist.TaskList;


/**
 * Exit command when executed will stop the Cluck instance from running.
 */
public class ExitCommand implements Command {
    public String execute(TaskList taskList, Storage storage) {
        return Messages.MESSAGE_GOODBYE;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }
}

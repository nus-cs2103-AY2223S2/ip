package cluck.commands;

import cluck.taskList.TaskList;
import cluck.messages.Messages;

public class ExitCommand implements Command{

    public String execute(TaskList taskList) {
        return Messages.MESSAGE_GOODBYE;
    }

    public static boolean equals(Command command) {
        return command instanceof ExitCommand;
    }
}

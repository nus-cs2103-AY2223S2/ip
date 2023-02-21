package cluck.commands;

import cluck.taskList.TaskList;
import cluck.messages.Messages;
import cluck.tasks.Task;

public class ExitCommand implements Command{

    public String execute(TaskList taskList) {
        return Messages.MESSAGE_GOODBYE;
    }

    public static boolean equals(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o != null && getClass() == o.getClass();
    }
}

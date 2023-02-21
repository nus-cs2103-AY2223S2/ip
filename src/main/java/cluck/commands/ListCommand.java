package cluck.commands;

import cluck.messages.Messages;
import cluck.taskList.TaskList;

/**
 * List command when executed simply return a list of all the tasks in the task list as a single string.
 */
public class ListCommand implements Command{

    public String execute(TaskList taskList) {
        return Messages.MESSAGE_LIST_DISPLAY + "\n" + taskList.toString();
    }
}

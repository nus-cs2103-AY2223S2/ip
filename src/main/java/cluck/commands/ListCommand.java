package cluck.commands;

import cluck.messages.Messages;
import cluck.storage.Storage;
import cluck.tasklist.TaskList;

/**
 * List command when executed simply return a list of all the tasks in the task list as a single string.
 */
public class ListCommand implements Command {

    public String execute(TaskList taskList, Storage storage) {
        return Messages.MESSAGE_LIST_DISPLAY + "\n" + taskList.toString();
    }
}

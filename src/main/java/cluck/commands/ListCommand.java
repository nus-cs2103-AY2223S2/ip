package cluck.commands;

import cluck.messages.Messages;
import cluck.taskList.TaskList;

public class ListCommand implements Command{

    public String execute(TaskList taskList) {
        String output = Messages.MESSAGE_LIST_DISPLAY + "\n" + taskList.listTasks();
        return output;
    }
}

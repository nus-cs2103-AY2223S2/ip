package Command;

import Storage.TaskList;

public abstract class Command {
    /**
     * abstract method to execute the user's request and return the response
     * @param taskList the list to store new task
     * @return response after execution of command
     */
    public abstract String execute(TaskList taskList);
}

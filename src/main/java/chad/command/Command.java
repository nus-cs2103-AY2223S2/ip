package chad.command;

import chad.storage.TaskList;

/**
 * Abstract class that is the parent class for all commands.
 */
public abstract class Command {
    /**
     * abstract method to execute the user's request and return the response
     * @param taskList the list to store new task
     * @return response after execution of command
     */
    public abstract String execute(TaskList taskList);
}

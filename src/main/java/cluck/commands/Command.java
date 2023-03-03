package cluck.commands;

import cluck.tasklist.TaskList;

/**
 * Abstract class for Commands, defines the default of commands. All Command types can be executed to run operations
 * and return the response of the operation. The response will vary depending on whether operations are
 * completed successfully and the type of operations done. The response is then handed to the Ui class to be displayed
 * to the user.
 */
public interface Command {
    String execute(TaskList taskList);
}

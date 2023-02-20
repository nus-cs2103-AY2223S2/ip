package cluck.commands;

import cluck.taskList.TaskList;

/**
 * Abstract class for Commands, defines the default of commands.
 */
public interface Command {
    String execute(TaskList taskList);
}

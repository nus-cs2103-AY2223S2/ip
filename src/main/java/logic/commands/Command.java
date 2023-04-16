package logic.commands;

import model.TaskList;

/**
 * Abstract class representing a command.
 */
public abstract class Command {

    public boolean isExit = false;

    public abstract String execute(TaskList taskList);

    /**
     * Returns true if the command is an exit command.
     * @return
     */
    public boolean isExit() {
        return this.isExit();
    }
}

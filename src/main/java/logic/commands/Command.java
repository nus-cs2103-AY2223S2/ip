package logic.commands;

import model.TaskList;

/**
 * Abstract class representing a command.
 */
public abstract class Command {

    private boolean isExit = false;

    public abstract String execute(TaskList taskList);

    /**
     * Returns true if the command is an exit command.
     * @return
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Sets the isExit flag to true.
     */
    public void setExit() {
        this.isExit = true;
    }
}

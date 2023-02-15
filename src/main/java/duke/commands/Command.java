package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This is a class that represents the commands for Duke program.
 */
public abstract class Command {

    /**
     * Constructor for Command class.
     */
    public Command() {}

    public abstract boolean isExit();

    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}

package duke.command;

import duke.DukeException;
import duke.ui.Ui;
import duke.task.TaskList;

/**
 * The command to be executed. All commands are subclasses of this class.
 */
public abstract class Command {
    protected TaskList taskList;
    protected Ui ui;

    public Command() {
    }

    /**
     * Assigns the taskList and ui to the command.
     * This is done after the command is created.
     * @param taskList The list of tasks in the program.
     * @param ui       The ui interface of the program.
     */
    public void assign(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public abstract boolean equals(Object obj);

    public abstract void execute() throws DukeException;

}

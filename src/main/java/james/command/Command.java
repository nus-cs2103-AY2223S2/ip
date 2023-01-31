package james.command;

import james.JamesException;
import james.task.TaskList;
import james.ui.UI;

/**
 * The command to be executed. All specific commands are subclasses of this class.
 */
public abstract class Command {
    protected TaskList taskList;
    protected UI ui;

    public Command() {
    }

    public void assign(TaskList taskList, UI ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public abstract boolean equals(Object obj);

    public abstract void execute() throws JamesException;

}

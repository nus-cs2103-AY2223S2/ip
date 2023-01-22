package duke.command;

import duke.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    protected TaskList taskList;
    protected Ui ui;

    public Command() {
    }

    public void assign(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public abstract boolean equals(Object obj);

    public abstract void execute() throws DukeException;

}

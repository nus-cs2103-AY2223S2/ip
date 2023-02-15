package duke.commands;

import duke.Ui;
import duke.exceptions.*;
import duke.task.TaskList;

public abstract class Command {
    protected Ui ui;
    protected TaskList taskList;

    public Command(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    public abstract String executeCommand(String userInput) throws NeroException;
}

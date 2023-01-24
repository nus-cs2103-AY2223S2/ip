package duke.commands;

import duke.tasks.TaskList;

public abstract class Command {

    protected TaskList taskList;
    protected String lineInput;

    public Command(TaskList taskList, String lineInput) {
        this.taskList = taskList;
        this.lineInput = lineInput;
    }

    public abstract void execute();
    public abstract void uiReply();
}

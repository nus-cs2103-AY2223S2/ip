package logic.commands;

import model.TaskList;

public abstract class Command {

    public boolean isExit;

    public abstract String execute(TaskList taskList);

    public boolean isExit() {
        return this.isExit();
    }
}

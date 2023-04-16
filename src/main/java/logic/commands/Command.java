package logic.commands;

import model.TaskList;

public abstract class Command {

    public boolean isExit = false;

    public abstract String execute(TaskList taskList);

    public boolean isExit() {
        return this.isExit();
    }
}

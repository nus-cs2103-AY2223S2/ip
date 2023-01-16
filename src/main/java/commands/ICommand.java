package commands;

import exceptions.DukeException;
import tasks.ITask;
import java.util.ArrayList;

public abstract class ICommand {
    public ArrayList<ITask> getTasks() {
        return tasks;
    }


    private final ArrayList<ITask> tasks;
    public ICommand(ArrayList<ITask> tasks) {
        this.tasks = tasks;
    }
    public abstract boolean run() throws DukeException;
}

package duke.commands;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.components.TaskList;
import duke.components.Ui;
import duke.components.Storage;

import java.util.ArrayList;

public abstract class AddCommand extends Command {

    private Task taskToAdd;
    public AddCommand(ArrayList<String> tokens) throws DukeException {
        super(tokens);
    }
    public void setTaskToAdd(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }
    public Task getTaskToAdd() {
        return this.taskToAdd;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(taskToAdd);
        ui.showAddCompletion(this, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    static final int commandLength = 2;
    static final int indexOfIndex = 1;

    private Task taskToDeleted;
    private int indexOfTask;
    public DeleteCommand(ArrayList<String> tokens) throws DukeException {
        super(tokens);
        if (tokens.size() != commandLength) {
            throw new DukeException(
                    "Invalid input received! \nDelete commands are in the form of: delete i \n(only 1 whitespace allowed)"
            );
        }
        this.indexOfTask = Integer.parseInt(tokens.get(indexOfIndex));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (indexOfTask < 1 || indexOfTask > tasks.size()){
            throw new DukeException(
                    "index " + indexOfTask +" not in range of tasklist!");
        }
        this.taskToDeleted = tasks.getTask(indexOfTask);
        tasks.deleteTask(indexOfTask);
        ui.displayDeleteMsg(this, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
    public Task getTaskToDelete() {
        return this.taskToDeleted;
    }
}

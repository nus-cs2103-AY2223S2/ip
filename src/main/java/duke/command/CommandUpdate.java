package duke.command;

import duke.task.Task;
import duke.task.TaskComponent;
import javafx.util.Pair;

import java.util.List;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


public class CommandUpdate extends Command {

    private int idx;
    private List<Pair<TaskComponent, ?>> updateComponents;

    /**
     * Constructor for an Update command.
     *
     * @param idx Index of the task to be updated.
     */
    public CommandUpdate(int idx, List<Pair<TaskComponent, ?>> updateComponents) {
        this.idx = idx;
        this.updateComponents = updateComponents;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task updatedTask = tasks.update(idx - 1, updateComponents);
        storage.save(tasks.getList());
        return "Task updated: " + updatedTask;
    }
}

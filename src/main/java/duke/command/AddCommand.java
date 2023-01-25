package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulation of the command to add a task.
 */
public class AddCommand extends Command {
    private Task task;
    /**
     * Constructor for AddCommand.
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }
    /**
     * Adds the given task to the taskList.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.add(this.task);
    }

    /**
     * Returns whether the program should exit or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the related fields and behavior of a command to add a task.
 */
public class AddCommand extends Command {
    private Task task;
    /**
     * Instantiates AddCommand.
     *
     * @param task The task to be added into the list.
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
     * Returns whether the command requires the program to exit.
     *
     * @return False indicating that program should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

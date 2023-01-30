package duke.command;
import duke.Storage;
import duke.Ui;
import duke.TaskList;
import duke.DukeException;
import duke.task.Task;

public class MarkCommand extends Command{
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param tasks The current list of tasks.
     * @param ui Ui displaying the results of execution.
     * @param storage Storage to update when tasks update.
     * @throws DukeException When the task cannot be executed or has an error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task markedTask = tasks.markTaskDone(index);
        storage.save(tasks);
        ui.showMarkTask(markedTask);
    }

    /**
     * Returns false.
     *
     * @return A boolean if the command is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }


    @Override
    public String toString() {
        return "Command: Mark task " + index;
    }
}

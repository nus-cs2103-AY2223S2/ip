package duke.command;
import duke.Storage;
import duke.Ui;
import duke.TaskList;
import duke.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public DeleteCommand() {
        super();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = tasks.deleteTask(index);
        storage.save(tasks);
        return ui.showDeleteTask(deletedTask, tasks);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String commandHelp() {
        return "Showing help for command: remove\n"
                + Ui.showSepLine()
                + "Removes a task from the list\n"
                + Ui.showSepLine()
                + "Usage:\n"
                + "remove [(int) INDEX]\n\n"
                + "Example:\n"
                + "remove 1\n"
                + Ui.showSepLine();
    }

    @Override
    public String toString() {
        return "Command: Delete task " + index;
    }
}

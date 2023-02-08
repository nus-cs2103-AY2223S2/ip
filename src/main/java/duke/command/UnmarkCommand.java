package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command that unmarks a task at an index as done when executed.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public UnmarkCommand() {
        super();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = tasks.markTaskUndone(index);
        storage.save(tasks);
        return ui.showUnmarkTask(unmarkedTask);
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
        return "Showing help for command: unmark\n"
                + Ui.showSepLine()
                + "Sets a task's state to undone\n"
                + Ui.showSepLine()
                + "Usage:\n"
                + "unmark [(int) INDEX]\n\n"
                + "Example:\n"
                + "unmark 1\n"
                + Ui.showSepLine();
    }

    @Override
    public String toString() {
        return "Command: Mark task " + index;
    }
}

package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command that marks a task as done when executed.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public MarkCommand() {
        super();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task markedTask = tasks.markTaskDone(index);
        storage.save(tasks);
        return ui.showMarkTask(markedTask);
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
        return "Showing help for command: mark\n"
                + Ui.showSepLine()
                + "Sets a task's state to done\n"
                + Ui.showSepLine()
                + "Usage:\n"
                + "mark [(int) INDEX]\n\n"
                + "Example:\n"
                + "mark 1\n"
                + Ui.showSepLine();
    }


    @Override
    public String toString() {
        return "Command: Mark task " + index;
    }
}

package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that lists current tasks added when executed.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTasks(tasks);
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
        return "Showing help for command: list\n"
                + Ui.showSepLine()
                + "Lists all current tasks added\n"
                + Ui.showSepLine()
                + "Usage:\n"
                + "list\n"
                + Ui.showSepLine();
    }

    @Override
    public String toString() {
        return "Command: List tasks";
    }
}

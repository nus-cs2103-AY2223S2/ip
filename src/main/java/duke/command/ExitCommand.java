package duke.command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that exits the application when executed
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String commandHelp() {
        return "Showing help for command: Exit\n"
                + Ui.showSepLine()
                + "Exits the application\n"
                + Ui.showSepLine()
                + "Usage:\n"
                + "exit\n"
                + Ui.showSepLine();
    }

    @Override
    public String toString() {
        return "Command: Exit";
    }
}

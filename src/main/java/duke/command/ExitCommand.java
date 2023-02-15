package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to exit the program.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    /**
     * Heeds user goodbye via the ui.
     *
     * @param tasks TaskList that contains all the current tasks
     * @param ui Ui that communicates with the user
     * @param storage Storage that backups the saving of tasks
     * @return string reply to be shown to user after executing this command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Goodbye! Pray to me and you shall hear from me again~\n";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

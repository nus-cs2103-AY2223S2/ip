package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Unknown command that is supplied.
 */
public class UnknownCommand extends Command {

    /**
     * Returns prompt that and unknown command is supplied.
     * @param taskList Tasklist containing current tasks.
     * @param ui Ui Component for input and output.
     * @param storage Storage component for persistent storage of Tasks.
     * @return String to be displayed by duke.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "Unknown command, please try again";
    }
}

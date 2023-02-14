package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ListCommand prints all the tasks in the current task list.
 */
public class ListCommand extends Command {

    /**
     * Creates a ListCommand.
     *
     * @param textCmd user input.
     */
    public ListCommand(String textCmd) {
        super(textCmd);
    }

    /**
     * Produces a String for the GUI to read from.
     * The String is the list of Tasks in the task list.
     *
     * @param ui User Interface of Duke.
     * @param storage storage of Duke.
     * @param taskList task list containing all the tasks Duke is tracking.
     * @return the String of all the tasks in the task list.
     */
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        return ui.showList(taskList);
    }
}

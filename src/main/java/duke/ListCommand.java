package duke;

/**
 * A command to list the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs the list command with given user input.
     *
     * @param input Input from user.
     */
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Executes the list command to display the list,
     * given task list, ui and storage.
     *
     * @param taskList TaskList for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke.
     * @throws DukeException If error occurs.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showList(taskList);
    }

    public String executeReturnString(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.formatShowList(taskList);
    }
}

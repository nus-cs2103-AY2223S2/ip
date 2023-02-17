package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Executable command to print out existing task list.
 *
 * @author Guo-KeCheng
 */
public class ListCommand extends Command {
    private final TaskList taskList;
    private final Ui ui;

    /**
     * ListCommand constructor
     *
     * @param taskList Existing taskList
     * @param ui       Shared Ui object
     */
    public ListCommand(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Iterate through the taskList
     * and prints out the status of each individual tasks.
     *
     * @throws DukeException if input is incorrect
     */
    @Override
    public String execute() throws DukeException {
        return ui.printList(taskList);
    }
}

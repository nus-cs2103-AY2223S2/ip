package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Ui;

/**
 * Executable command to print out existing task list.
 *
 * @author Guo-KeCheng
 */
public class ListCommand extends Command {
    private TaskList taskList;
    private Ui ui;

    /**
     * ListCommand constructor
     *
     * @param taskList Existing taskList
     * @param ui Shared Ui object
     */
    public ListCommand(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /*
     * execute in printCommand iterates through the taskList
     * and prints out the status of each individual tasks
     */
    @Override
    public boolean execute() throws DukeException {
        ui.printList(taskList);

        return false;
    }
}

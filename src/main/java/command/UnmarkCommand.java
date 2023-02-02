package command;

import duke.Storage;
import duke.Ui;
import exceptions.DukeException;
import exceptions.InvalidNumberException;
import tasks.TaskList;

/**
 * This class handles unmarking a task as incomplete
 */

public class UnmarkCommand extends Command {
    private int index;

    /***
     * Constructor for command.UnmarkCommand
     * @param index
     */

    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    /***
     * marks task as done and update storage
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int taskNumMinusOne = index - 1;
        if (taskNumMinusOne < 0 || taskNumMinusOne > taskList.size() - 1) {
            throw new InvalidNumberException();
        }
        taskList.unmarkTask(index);
        storage.writeFile(taskList);
        return ui.printUnmark(index, taskList.get(taskNumMinusOne));
    }
}

package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;


/**
 * Executable command to sort the list.
 *
 * @author Guo-KeCheng
 */
public class SortCommand extends Command {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    /**
     * SortCommand constructor
     *
     * @param taskList Existing taskList
     * @param ui       Shared Ui object
     * @param storage  Shared storage object
     */
    public SortCommand(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    public String execute() throws DukeException {
        return ui.printSortedList(taskList);
    }
}

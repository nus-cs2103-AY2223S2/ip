package duke.command;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Class to exectue the command to sort
 */
public class SortCommand extends Command {

    /**
     * Sorts the task in tasklist, and then update the task in data.txt
     *
     * @param tl Tasklist to sort
     * @param ui Ui to give user output after sorting
     * @param storage Storage which will be updated during sorting
     * @return Tasks in sorted order
     * @throws DukeException If there is error in sorting
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        tl.sortTaskList(storage);
        return "Results sorted!\n" + ui.showGetAllTaskResult(tl);
    }
}

package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents Duke's unmark function.
 */
public class UnmarkCommand extends Command {
    /** Contructs an unmark command. */
    public UnmarkCommand() {}

    /**
     * Unmarks a task and stores the resulting tasklist.
     *
     * @throws DukeException If user input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        Integer taskNum = ui.getTaskNum();
        String s = tasks.unmark(taskNum);
        store.saveToFile(tasks);
        return ui.printWithPartition("\tOK, I've marked this task as not done yet:" + "\n\t  " + s + "\n");
    };

}

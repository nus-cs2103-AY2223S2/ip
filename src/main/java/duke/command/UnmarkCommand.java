package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command: mark the item as undone
 */
public class UnmarkCommand extends Command {
    private int taskNo;

    /**
     * Creates the command to unmark task as done
     *
     * @param taskNo int index of task in the ArrayList
     */
    public UnmarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes the command
     *
     * @param tasks   TaskList object to get and set the list
     * @param ui      object to reply to user after the command has executed
     * @param storage object required when command writes to file
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.get(taskNo).unmarkAsDone();
        ui.showUnmarkDone(tasks, taskNo);
        storage.save(tasks);
    }

    /**
     * Checks if this command will exit the program
     *
     * @return boolean True if the command will exit the program
     */
    public boolean isExit() {
        return false;
    }
}

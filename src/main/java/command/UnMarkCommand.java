package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.Tasklist;

/**
 * The UnMarkCommand class implements the Command interface and
 * represents the command for unmarking a task in the task list.
 */
public class UnMarkCommand implements Command {
    private int index;

    /**
     * The index of the task to be unmarked
     */
    public UnMarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmarks the task at the specified index in the task list
     * @param ui The user interface used to display information to the user
     * @param list The task list being operated on
     * @param storage The storage backend used to persist the task list
     */
    @Override
    public String execute(Ui ui, Tasklist list, Storage storage) {
        int oneIndex = this.index + 1;
        Task unMarkedTask = list.getTask(oneIndex);
        list.unmarkTask(this.index);
        return ui.getUnMarkReply(unMarkedTask);
    }

}

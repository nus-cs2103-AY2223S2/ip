package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.Tasklist;

/**
 * The MarkCommand class implements the Command interface and
 * represents a command to mark a task in the task list as done.
 */
public class MarkCommand implements Command {
    private int index;

    /**
     * Constructor that creates a MarkCommand object with the specified task index.
     * @param index the index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command by marking the task with the specified index in the
     * task list as done.
     * @param ui the user interface to use for input/output
     * @param list the task list
     * @param storage the storage backend to use for persistence
     */
    @Override
    public String execute(Ui ui, Tasklist list, Storage storage) {
        int oneIndex = this.index + 1;
        Task markedTask = list.getTask(oneIndex);
        list.markTaskAsDone(this.index);
        return ui.getMarkReply(markedTask);
    }

}

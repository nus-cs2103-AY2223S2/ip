package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Mark/Unmark command.
 */
public class CommandMark extends Command {
    private int idx;
    private boolean isDone;

    /**
     * Constructor for a Mark/Unmark command.
     *
     * @param idx Index of the task to be marked/unmarked.
     * @param isDone Status of the task to be marked/unmarked.
     */
    public CommandMark(int idx, boolean isDone) {
        this.idx = idx;
        this.isDone = isDone;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTask(idx - 1, isDone);
        storage.save(tasks.getList());
        return ui.formResponse("LeTask marked as " + (isDone ? "done: " : "undone: ")
                + tasks.getTask(idx - 1));
    }
}

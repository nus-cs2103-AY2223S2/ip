package duke.commands;

import duke.Storage;
import duke.TaskException;
import duke.TaskList;

/**
 * Command that marks as done or undone
 */
public class MarkDoneCommand extends Command {
    private final boolean isDone;
    private final int index;

    /**
     * Creates a new MarkDone command
     *
     * @param isDone whether to mark as done
     * @param index  index of item
     */
    public MarkDoneCommand(boolean isDone, int index) {
        this.isDone = isDone;
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskException {
        if (isDone) {
            tasks.mark(index - 1);
        } else {
            tasks.unmark(index - 1);
        }
        storage.store(tasks);
        return tasks.toString();
    }
}

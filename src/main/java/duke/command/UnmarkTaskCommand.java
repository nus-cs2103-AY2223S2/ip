package duke.command;

import duke.DukeException;
import duke.task.Task;

/**
 * The command to mark a task as not done.
 */
public class UnmarkTaskCommand extends Command {
    private final int index;

    public UnmarkTaskCommand(int taskNumber) {
        this.index = taskNumber;
    }

    @Override
    public String execute() throws DukeException {
        if (index >= taskList.getSize()) {
            throw new DukeException("Task number is out of range!");
        }
        Task task = taskList.getTask(index);
        task.markAsNotDone();
        return ui.printTaskUnmarked(task);
    }

    @Override
    public String toString() {
        return String.format("UnmarkTaskCommand{index=%d}", index);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnmarkTaskCommand) {
            return index == ((UnmarkTaskCommand) obj).index;
        }
        return false;
    }
}

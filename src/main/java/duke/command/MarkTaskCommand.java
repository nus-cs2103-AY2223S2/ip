package duke.command;

import duke.DukeException;
import duke.task.Task;

/**
 * Marks a task as done.
 */
public class MarkTaskCommand extends Command {
    private final int index;

    public MarkTaskCommand(int taskNumber) {
        this.index = taskNumber;
    }

    @Override
    public String execute() throws DukeException {
        assert taskList != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        if (index >= taskList.getSize()) {
            throw new DukeException("Task number is out of range!");
        }
        Task task = taskList.getTask(index);
        task.markAsDone();
        return ui.printTaskMarked(task);
    }

    @Override
    public String toString() {
        return String.format("MarkTaskCommand{index=%d}", index);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MarkTaskCommand) {
            return index == ((MarkTaskCommand) obj).index;
        }
        return false;
    }
}

package duke.command;

import duke.exception.DukeException;
import duke.exception.TaskIndexException;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class MarkCommand extends Command{
    private final int index;

    public MarkCommand(int inputIndex) {
        this.index = inputIndex - 1;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            Task task = tasks.markTask(this.index);
            return ui.showMarked(task);
        } catch (IndexOutOfBoundsException exception) {
            throw new TaskIndexException(1 + tasks.size());
        }
    }
}

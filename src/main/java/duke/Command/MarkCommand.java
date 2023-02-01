package duke.Command;

import duke.Exceptions.DukeException;
import duke.Exceptions.TaskIndexException;
import duke.Storage;
import duke.Tasks.Task;
import duke.Tasks.TaskList;
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
            ui.showMarked(task);
            return task.toString();
        } catch (IndexOutOfBoundsException exception) {
            throw new TaskIndexException(1 + tasks.size());
        }
    }
}

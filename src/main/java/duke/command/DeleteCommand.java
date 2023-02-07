package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.task.Task;
import duke.main.Tasklist;
import duke.main.Ui;
import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public String execute(Tasklist tasklist, Ui ui, Storage storage) throws IOException, DukeException {
        try {
            Task task = tasklist.getTasks().get(this.taskNum - 1);
            tasklist.deleteTask(this.taskNum - 1);
            storage.update(tasklist);
            return ui.printDeleteTaskMessage(task, tasklist);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Task number to be unmarked is out of bounds of current list. " +
                    "Please use a task number within current list.");
        }
    }
}
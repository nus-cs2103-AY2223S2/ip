package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.Task;
import duke.main.Tasklist;

import java.io.IOException;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    public String execute(Tasklist tasklist, Ui ui, Storage storage) throws IOException, DukeException {
        if (tasklist.containsTask(this.task)) {
            throw new DukeException("☹ OOPS!!! This task already exists.");
        }
        else {
            int initialSize = tasklist.getTasksNum();
            tasklist.addTask(this.task);
            assert tasklist.getTasksNum() == initialSize + 1 : "tasklist size must increment after addition of task";
            storage.update(tasklist);
            return ui.printAddTaskMessage(this.task, tasklist);
        }
    }
}
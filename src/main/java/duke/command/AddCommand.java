package duke.command;

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

    public String execute(Tasklist tasklist, Ui ui, Storage storage) throws IOException {
        tasklist.addTask(this.task);
        storage.update(tasklist);
        return ui.printAddTaskMessage(this.task, tasklist);
    }
}
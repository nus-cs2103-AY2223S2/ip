package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

public class AddCommand extends Command {
    Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task cur = tasks.add(task);
        storage.refresh(tasks);
        String msg = "Got it. I've added this task:\n";
        msg += cur + "\n";
        msg += "Now you have " + tasks.size() +" tasks in the list.";
        ui.show(msg);
    }
}

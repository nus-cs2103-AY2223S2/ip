package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task t) {
        this.task = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        ui.customMessage("Added : " + task.getValue());
        ui.showNumberOfListings(tasks.size());
        storage.write(this.task);

    }
}


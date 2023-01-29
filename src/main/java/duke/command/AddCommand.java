package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command to add Todo, Deadline and Event tasks.
 */
public class AddCommand extends Command {
    private Task t;

    public AddCommand(Task t) {
        super();
        this.t = t;
    }

    /**
     * Adds task to tasks and save them in the storage file.
     * Informs user of successful execution of command via the ui.
     * @param tasks
     * @param ui
     * @param storage
     * @throws IOException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(this.t);
        ui.showMessage("Ok, I've added this task: ");
        ui.showMessage(t.toString());
        String msg = String.format("You now have %d task(s) in your list!", tasks.size());
        ui.showMessage(msg);
        storage.saveTasks(tasks.getTasks());
    }
}

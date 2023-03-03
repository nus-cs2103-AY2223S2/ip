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
    private Task task;

    /**
     * Constructor for this Add command.
     *
     * @param task to be added.
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Adds task to tasks and save them in the storage file.
     * Informs user of successful execution of command via the ui.
     *
     * @param tasks TaskList that contains all the current tasks.
     * @param ui Ui that communicates with the user.
     * @param storage Storage that backups the saving of tasks.
     * @return string reply to be shown to user after executing this command.
     * @throws IOException when storage file cannot be read.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(this.task);
        String msg = "Ok, time to add this task! Its priority will be medium by default: \n";
        msg += task.toString();
        msg += "\n";
        msg += String.format("You now have %d task(s) in your list! \n", tasks.size());
        storage.saveTasks(tasks.getTasks());
        return msg;
    }
}

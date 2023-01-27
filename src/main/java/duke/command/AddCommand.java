package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;

/**
 * AddCommand - If User enters the todo , deadline or the event command.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * public constructor for AddCommand
     */
    public AddCommand(Task t) {
        this.task = t;
    }

    /**
     * Adds ToDo,Deadline or Event tasks to the tasklist and storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        ui.customMessage("Added : " + task.getValue());
        ui.showNumberOfListings(tasks.size());
        storage.write(this.task);

    }
}


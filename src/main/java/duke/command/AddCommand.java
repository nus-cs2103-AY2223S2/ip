package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.gui.Ui;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        storage.write(this.task);
        String message = "Added : " + task.getValue() + "\n"
                + ui.showNumberOfListings(tasks.size());

        return message;
    }
}


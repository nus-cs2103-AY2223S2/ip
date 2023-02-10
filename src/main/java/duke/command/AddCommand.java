package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.gui.Ui;
import duke.task.Task;

/**
 * AddCommand - If User enters the todo , deadline or the event command.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructor
     */
    public AddCommand(Task t) {
        this.task = t;
    }

    /**
     * Adds ToDo,Deadline or Event tasks to the Tasklist and storage
     * @return a success message that shows the task added and the total number of tasks
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


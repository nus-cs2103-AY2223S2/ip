package duke.command;

import duke.display.Ui;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;

import java.io.IOException;

/**
 * A more specific instruction class that encapsulates the action of adding a task
 * into the given TaskList.
 */

public class AddTaskCommand extends Command {
    private final DukeTask task;

    /**
     * The constructor of AddTaskCommand that takes in the task to be added.
     *
     * @param task The task to be added
     */
    public AddTaskCommand(DukeTask task) {
        this.task = task;
    }

    /**
     * Adds the given task to the TaskList and display relevant information with the customized format.
     *
     * @param tasks The user TaskList that contains all the task to be manipulated
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(task);
        storage.save(tasks);
        String message = "Got it. I've added this task:\n " + task
                + "\nNow you have " + tasks.remainingTasks() + " tasks in the list.";
        ui.displayWithBar(message);
    }
}

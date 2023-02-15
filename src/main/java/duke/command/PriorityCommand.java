package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Priority;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command to modify the priority level
 * of an existing task.
 */
public class PriorityCommand extends Command {
    private final int taskIndex;
    private final Priority priorityLevel;

    public PriorityCommand(int taskIndex, Priority priorityLevel) {
        this.taskIndex = taskIndex;
        this.priorityLevel = priorityLevel;
    }

    /**
     * Modifies the priority of the intended task from tasks and
     * updates the storage file accordingly.
     * Informs user of successful execution of command via the ui.
     *
     * @param tasks TaskList that contains all the current tasks
     * @param ui Ui that communicates with the user
     * @param storage Storage that backups the saving of tasks
     * @return string reply to be shown to user after executing this command
     * @throws IOException when storage file cannot be read
     * @throws DukeException when user input does not comply with intended uses
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (taskIndex >= tasks.size()) {
            String errorMessage = String.format("Task %d does not exist!", taskIndex + 1);
            throw new DukeException(errorMessage);
        }

        Task task = tasks.get(this.taskIndex);
        task.setPriority(this.priorityLevel);
        String msg = "Ok, I've updated the priority of the following task: \n";
        msg += task.toString();
        msg += "\n";
        storage.saveTasks(tasks.getTasks());
        return msg;
    }
}

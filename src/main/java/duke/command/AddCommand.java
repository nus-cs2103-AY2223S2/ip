package duke.command;

import java.io.IOException;
import java.util.Objects;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command to add tasks to the TaskList
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor
     * @param task Task to be added to TaskList
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the new Task to the TaskList
     * @param tasks TaskList to be updated if needed
     * @param ui Ui for displaying messages in a unique way
     * @param storage Storage for updating local tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = "";
        tasks.add(task);
        result = ui.changeToFormat("Task successfully added:\n    " + task);
        try {
            storage.updateLocal(tasks);
        } catch (IOException e) {
            result += ("\nfailed to update tasks locally: " + e.getMessage());
        }
        assert
            Objects.equals(result, "-->-->-->-->-->-->-->-->-->-->-->\n    " + "Task successfully added:\n    " + task
                + "\n<--<--<--<--<--<--<--<--<--<--\n\n") : "wrong add message";
        return result;
    }
}

package duke.command;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Command that handles adding tasks
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
     * on execution, add the new Task to the TaskList
     * @param tasks TaskList to be updated if needed
     * @param ui Ui for displaying messages in a unique way
     * @param storage Storage for updating local tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.printToFormat("Task successfully added:\n    " + task);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            System.out.println("failed to update tasks locally: " + e.getMessage());
        }
    }
}
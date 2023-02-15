package clippy.command;

import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.task.ToDo;
import clippy.ui.Ui;

/**
 * Command handler for the `todo` command.
 *
 * @author chunzkok
 */
public class AddToDoCommand extends AddCommand {
    private String description;

    /**
     * Creates a new AddToDoCommand instance.
     *
     * @param description Description of the task to be added.
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a new ToDo to the TaskList.
     *
     * @param ui The Ui instance of the current run.
     * @param taskList The TaskList instance of the current run.
     * @param storage The Storage instance of the current run.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.add(new ToDo(description));
        super.printCreatedTaskStatus(taskList, ui);
    }
}

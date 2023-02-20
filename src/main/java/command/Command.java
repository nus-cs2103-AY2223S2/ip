package command;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents an executable command
 */
public abstract class Command {
    /**
     * Executes the task
     * @param taskList
     * @param storage
     * @param ui
     * @return Task message
     */
    public abstract String execute(TaskList taskList, Storage storage, Ui ui);
}

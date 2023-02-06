package dude.command;

import dude.exception.DudeException;
import dude.storage.Storage;
import dude.task.Task;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Command to add Task into TaskList.
 */
public class AddCommand extends Command {
    private final Task newTask;

    /**
     * Initializes AddCommand.
     *
     * @param newTask Task that has to be added to TaskList.
     */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addTask(newTask);
            storage.saveData(tasks);
            return ui.showAdd(newTask);
        } catch (DudeException e) {
            return ui.showError(e.getMessage());
        }
    }
}

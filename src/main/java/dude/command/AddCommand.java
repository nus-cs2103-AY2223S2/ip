package dude.command;

import dude.storage.Storage;
import dude.task.Task;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Command to add Task into TaskList
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(newTask);
        storage.saveData(tasks);
        ui.showAdd(newTask);
    }
}

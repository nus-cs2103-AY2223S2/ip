package catbot.commands;

import java.util.ArrayList;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.Task;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

/**
 * Handles deleting a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Initialises a new instance of DeleteCommand.
     * @param index is the zero-indexed index of the item to delete form the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CatBotException {
        tasks.delete(index);
        ui.setNextOutput("Deleted task.");
        storage.writeToSaveFile("delete " + (index + 1) + "\n");
    }

    @Override
    public void loadCommand(ArrayList<Task> tasks) {
        tasks.remove(index);
    }
}

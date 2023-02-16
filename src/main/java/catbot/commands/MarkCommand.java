package catbot.commands;

import java.util.ArrayList;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.Task;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

/**
 * Handles marking and unmarking tasks.
 */
public class MarkCommand extends Command {
    private final int index;
    private final boolean isDone;

    /**
     * Initialises a MarkCommand instance.
     * @param index is the zero-indexed index of the task to mark or unmark.
     * @param isDone is true if marking and untrue if unmarking.
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CatBotException {
        tasks.mark(index, isDone);
        ui.setNextOutput("Marked the task as done!");
        storage.writeToSaveFile("mark " + (index + 1) + "\n");
    }

    @Override
    public void loadCommand(ArrayList<Task> tasks) {
        assert index >= -1 && index < tasks.size(); // Values set during writing to file shouldn't be out of range
        tasks.get(Math.floorMod(index, tasks.size())).setDone(true); // Using floorMod handles -1 case for last index
    }
}

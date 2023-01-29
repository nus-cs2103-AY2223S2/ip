package catbot.commands;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.Task;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

import java.util.ArrayList;

public class MarkCommand extends Command {
    private final int index;
    private final boolean isDone;

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
        tasks.get(Math.floorMod(index, tasks.size())).setDone(true); // Using floorMod handles -1 case for last index
    }
}

package catbot.commands;

import catbot.CatBotException;
import catbot.storage.Storage;
import catbot.tasklist.Task;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

import java.util.ArrayList;

public class DeleteCommand extends Command{
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CatBotException {
        tasks.delete(index - 1);
        ui.setNextOutput("Deleted task.");
        storage.writeToSaveFile("delete " + (index + 1) + "\n");
    }

    @Override
    public void loadCommand(ArrayList<Task> tasks) {
        tasks.remove(index);
    }
}

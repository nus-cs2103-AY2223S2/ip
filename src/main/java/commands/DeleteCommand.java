package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import views.UI;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(UI ui, TaskList tasks, Storage storage) throws DukeException {
        try {
            Task deletedTask = tasks.delete(index);
            this.commandStatus = "Removed task: " + deletedTask + "\n"
                    + "You now have " + tasks.size() + " task(s) in your list.";
            ui.printCommandOutput(this);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide a valid index!");
        }
    }
}

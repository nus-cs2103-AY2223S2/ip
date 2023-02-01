package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import views.UI;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(UI ui, TaskList tasks, Storage storage) throws DukeException {
        try {
            tasks.markDone(index);
            this.commandStatus = "Good job! I have marked this task as done! \n" + "\t" + tasks.get(index - 1);
            ui.printCommandOutput(this);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide a valid index!");
        }
    }
}

package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.views.UI;

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
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide a valid index!");
        }
    }
}

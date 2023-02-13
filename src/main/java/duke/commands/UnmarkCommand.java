package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.views.UI;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(UI ui, TaskList tasks, Storage storage) throws DukeException {
        try {
            tasks.markUndone(index);
            this.commandStatus = "Oof! I have marked this task as undone for you! \n"
                    + "\t" + tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide a valid index!");
        }
    }
}

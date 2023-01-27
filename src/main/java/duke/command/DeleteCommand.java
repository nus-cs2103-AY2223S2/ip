package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * DeleteCommand - If user enters the delete command.
 */
public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    /**
     *  Deletes the given task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.customMessage("Noted. I've removed this task: \n");
        ui.customMessage(tasks.get(index));
        tasks.remove(index);
        ui.showNumberOfListings(tasks.size());
    }
}

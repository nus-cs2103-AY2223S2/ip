package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Mark Command - User enters the mark command
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Public constructor for MarkCommand
     * @param index receive index from user to know which to mark.
     *         - 1 because our list is 0-index basing.
     */
    public MarkCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Mark the given task in the list and storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(index);
        ui.showMarkSucess(tasks.get(index));
    }
}

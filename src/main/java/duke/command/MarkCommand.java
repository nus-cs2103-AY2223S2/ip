package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

/**
 * The type Mark command.
 */
public class MarkCommand extends Command{

    private int index;
    private boolean isMarked;

    /**
     * Instantiates a new Mark command.
     *
     * @param index    the index
     * @param isMarked the is marked
     */
    public MarkCommand(int index, boolean isMarked) {
        super();
        this.index = index;
        this.isMarked = isMarked;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (isMarked) {
            ui.showAction(tasks.markTask(index));
        } else {
            ui.showAction(tasks.unmarkTask(index));
        }
    }
}

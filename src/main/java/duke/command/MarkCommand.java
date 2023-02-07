package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The type Mark command.
 */
public class MarkCommand extends Command {

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (isMarked) {
            return tasks.markTask(index);
        } else {
            return tasks.unmarkTask(index);
        }
    }
}

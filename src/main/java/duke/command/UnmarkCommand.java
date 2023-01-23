package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/** A representation of the "unmark" command in Duke. */
public class UnmarkCommand extends Command {

    private final String NAME = "unmark";
    private int index;

    /**
     * Initializes a unmark command with a given index.
     * 
     * @param index The index of the task to be marked as not done yet
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.markTaskAsUndone(this.index);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        UnmarkCommand cmd = (UnmarkCommand) obj;
        return this.index == cmd.index;
    }

}

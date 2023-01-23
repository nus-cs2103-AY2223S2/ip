package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/** A representation of the "mark" command in Duke. */
public class MarkCommand extends Command {

    private final String NAME = "mark";
    private int index;

    /**
     * Initializes a mark command with a given index.
     * 
     * @param index The index of the task to be marked as done
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.markTaskAsDone(this.index);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        MarkCommand cmd = (MarkCommand) obj;
        return this.index == cmd.index;
    }
}

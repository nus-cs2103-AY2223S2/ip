package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/** A representation of the "delete" command in Duke. */
public class DeleteCommand extends Command {

    private final String NAME = "delete";
    private int index;

    /**
     * Initializes a unmark command with a given index.
     * 
     * @param index The index of the task to be marked as not done yet
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.deleteTask(this.index);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        DeleteCommand cmd = (DeleteCommand) obj;
        return this.index == cmd.index;
    }

}

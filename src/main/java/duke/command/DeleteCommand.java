package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * The DeleteCommand class encapsulates the variables and methods related to Delete commands.
 */
public class DeleteCommand extends Command {
    public static final String DELETE_COMMAND = "delete";
    private final int index;

    /**
     * Constructor creates an instance of DeleteCommand.
     * @param index int index of task to be deleted.
     */
    public DeleteCommand(int index) {
        super(DELETE_COMMAND);
        this.index = index;
    }

    @Override
    public String execute(TaskList lst, Ui ui) throws DukeException {
        Task t = lst.getTask(this.index);
        lst.deleteTask(this.index);
        return ui.showDeletedTask(t, lst.getSize());
    }
}

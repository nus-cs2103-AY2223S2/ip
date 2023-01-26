package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    public static final String DELETE_COMMAND = "delete";
    private final int index;

    public DeleteCommand(int index) {
        super(DELETE_COMMAND);
        this.index = index;
    }

    @Override
    public void execute(TaskList lst, Ui ui) throws DukeException {
        Task t = lst.getTask(this.index);
        lst.deleteTask(this.index);
        ui.showDeletedTask(t, lst.getSize());
    }
}

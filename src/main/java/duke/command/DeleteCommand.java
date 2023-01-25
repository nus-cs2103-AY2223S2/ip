package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("\tYou currently have no tasks in your list to delete.");
        } else {
            try {
                Task deletedTask = task.deleteTask(index);
                int size = task.getSize();
                ui.showDelete(deletedTask, size);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("\tTask number does not exist!");
            }
        }
    }
}

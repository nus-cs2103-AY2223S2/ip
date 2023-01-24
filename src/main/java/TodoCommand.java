import DukeExceptions.DukeEmptyTaskException;
import DukeExceptions.DukeStoreFullException;

/**
 * The command that creates a Todo task.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class TodoCommand extends Command {
    private final String task;
    public TodoCommand(String task) {
        this.task = task;
    }

    public TodoCommand() {
        this.task = "";
    }

    @Override
    public void execute(TaskList ts, Ui ui) {
        if (this.task.length() == 0) {
            ui.error(new DukeEmptyTaskException(DukeEmptyTaskException.TaskType.Todo));
            return;
        }

        TaskTodo todo = new TaskTodo(task);
        try {
            ts.add(todo);
        } catch (DukeStoreFullException e) {
            ui.error(e);
        }
    }
}

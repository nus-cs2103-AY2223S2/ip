package duke.command;

import duke.exceptions.DukeEmptyTaskException;
import duke.exceptions.TaskListFullException;
import duke.TaskList;
import duke.task.TodoTask;
import duke.Ui;

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
            ui.error(new DukeEmptyTaskException(DukeEmptyTaskException.TaskType.TYPE_Todo));
            return;
        }

        TodoTask todo = new TodoTask(task);
        try {
            ts.add(todo);
        } catch (TaskListFullException e) {
            ui.error(e);
        }
    }
}

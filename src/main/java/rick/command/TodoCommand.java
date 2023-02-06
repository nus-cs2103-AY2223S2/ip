package rick.command;

import rick.TaskList;
import rick.Ui;
import rick.exceptions.RickEmptyTaskException;
import rick.exceptions.TaskListFullException;
import rick.task.TodoTask;

/**
 * The command that creates a Todo task.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class TodoCommand extends Command {
    private final String task;

    /**
     * Given a task description, creates a Todo Task when executed.
     *
     * @param task The task description to store.
     */
    public TodoCommand(String task) {
        this.task = task;
    }

    /**
     * Formats this as a task with an empty description.
     */
    public TodoCommand() {
        this.task = "";
    }

    /**
     * Executes this task.
     *
     * @param ts The TaskList instance.
     * @param ui The UI instance.
     * @return The UI to display from the GUI.
     */
    @Override
    public String execute(TaskList ts, Ui ui) {
        if (this.task.length() == 0) {
            return ui.error(new RickEmptyTaskException(RickEmptyTaskException.TaskType.TYPE_Todo));

        }

        TodoTask todo = new TodoTask(task);
        try {
            return ts.add(todo);
        } catch (TaskListFullException e) {
            return ui.error(e);
        }
    }
}

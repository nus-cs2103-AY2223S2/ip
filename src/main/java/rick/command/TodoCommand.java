package rick.command;

import rick.TaskList;
import rick.Ui;
import rick.exceptions.RickEmptyTaskException;
import rick.exceptions.TaskListFullException;
import rick.task.TodoTask;

/**
 * Represents the command that creates a Todo task.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class TodoCommand extends Command {
    private final String task;

    /**
     * Constructs a Command instance when given the task description.
     *
     * @param task The task description to store.
     */
    public TodoCommand(String task) {
        this.task = task;
    }

    /**
     * Constructs a Command with an empty description.
     */
    public TodoCommand() {
        this.task = "";
    }

    /**
     * Executes this command with the given TaskList and UI output, and
     * returns the UI to output to the user.
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

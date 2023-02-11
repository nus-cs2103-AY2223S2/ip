package duke.commands.taskCommand;

import java.util.List;

import duke.Duke;
import duke.commands.Command;
import duke.task.Task;

/**
 * A subclass of {@link Command Commands} that return tasks to be added to the TaskList. 
 * Override the {@link #getTask(String[], Duke) getTask()} function
 * to supply a task at the end of command execution.
 */
public abstract class TaskCommand<T extends Task> extends Command {
    public TaskCommand(String label) {
        super(label);
    }

    @Override
    protected final void executeInternal(String[] tokens, Duke instance) throws ValidationException {
        T task = getTask(tokens, instance);

        List<Task> tasks = instance.getTaskList();
        tasks.add(task);
        output("Added %s to the list!\nYou now have %d tasks\n", task.toString(), tasks.size());
    }

    /**
     * Called when the command is executed to return a {@link Task task} to be added to the Duke instance. 
     * Follows the calling conventions of {@link #executeInternal(String[], Duke) execute}.
     * @throws ValidationException
     */
    protected abstract T getTask(String[] tokens, Duke instance) throws ValidationException;
}

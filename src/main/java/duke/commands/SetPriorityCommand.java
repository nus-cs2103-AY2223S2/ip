package duke.commands;

import duke.Storage;
import duke.TaskException;
import duke.TaskList;
import duke.task.Task;

/**
 * Command that sets priority for a task
 */
public class SetPriorityCommand extends Command {
    private final int index;
    private final Task.Priority priority;

    /**
     * Creates a new command to set priority
     *
     * @param index    index of item
     * @param priority priority to set
     */
    public SetPriorityCommand(int index, Task.Priority priority) {
        this.index = index;
        this.priority = priority;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws TaskException {
        tasks.setPriority(index, priority);
        storage.store(tasks);
        return "Priority set, new list: " + tasks;
    }
}

package chad.commands;

import chad.backend.TaskList;
import chad.tasks.Task;

/**
 * Command to Mark a Task as not done.
 */
public class Unmark extends Command {
    private final int idx;
    private final TaskList tasklist;

    /**
     * Constructor for an Unmark command.
     * @param idx 1-index of the Task to be unmarked.
     * @param tasklist The list to unmark the Task from.
     */
    public Unmark(int idx, TaskList tasklist) {
        this.idx = idx;
        this.tasklist = tasklist;
    }

    @Override
    public String execute() {
        assert (idx <= tasklist.getWholeList().size());
        Task t = tasklist.get(idx - 1);
        t.unmark();
        return "Ok Boss, I've marked this task as not done:\n" + t + "\n";
    }
}

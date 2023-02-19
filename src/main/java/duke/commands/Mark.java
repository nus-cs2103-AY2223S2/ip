package duke.commands;

import duke.backend.TaskList;
import duke.tasks.Task;

/**
 * Command to Mark a Task as done.
 */
public class Mark extends Command {
    private int idx;
    private TaskList tasklist;

    /**
     * Constructor for a Mark command.
     * @param idx The 1-index of the Task to mark.
     * @param tasklist The list to mark the Task from.
     */
    public Mark(int idx, TaskList tasklist) {
        this.idx = idx;
        this.tasklist = tasklist;
    }

    @Override
    public String execute() {
        assert (idx <= tasklist.getWholeList().size());
        Task t = tasklist.get(idx - 1);
        t.mark();
        return "Nice! I've marked this task as done:\n" + t + "\n";
    }
}

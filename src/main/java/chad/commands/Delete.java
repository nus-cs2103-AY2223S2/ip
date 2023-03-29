package chad.commands;

import chad.backend.TaskList;
import chad.tasks.Task;

/**
 * Command for Deletion.
 */
public class Delete extends Command {

    private final int idx;
    private final TaskList tasklist;

    /**
     * Constructor for Delete command.
     * @param idx 1-index of Task to be deleted.
     * @param tasklist The list to be deleted from.
     */
    public Delete(int idx, TaskList tasklist) {
        this.idx = idx;
        this.tasklist = tasklist;
    }

    @Override
    public String execute() {
        assert (idx <= tasklist.getWholeList().size());
        Task t = tasklist.delete(idx - 1);
        String res = "Alright! I've deleted this task: " + t.toString() + "\n";
        return res;
    }
}

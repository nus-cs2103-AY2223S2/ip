package duke.commands;

import duke.backend.TaskList;
import duke.tasks.Task;

public class Delete extends Command {

    int idx;
    TaskList tasklist;

    public Delete(int idx, TaskList tasklist) {
        this.idx = idx;
        this.tasklist = tasklist;
    }

    @Override
    public String execute() {
        Task t = tasklist.delete(idx - 1);
        String res = "OK! I've deleted the following task: " + t.toString() + "\n";
        return res;
    }
}

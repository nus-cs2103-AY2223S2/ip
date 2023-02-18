package duke.commands;

import duke.backend.TaskList;
import duke.tasks.Task;

public class Unmark extends Command {
    private int idx;
    private TaskList tasklist;

    public Unmark(int idx, TaskList tasklist) {
        this.idx = idx;
        this.tasklist = tasklist;
    }

    @Override
    public String execute() {
        Task t = tasklist.get(idx - 1);
        t.unmark();
        return "OK! I've marked this task as not done:\n" + t + "\n";
    }
}

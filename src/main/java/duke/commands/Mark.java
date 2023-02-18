package duke.commands;

import duke.backend.TaskList;
import duke.tasks.Task;

public class Mark extends Command {
    private int idx;

    private TaskList tasklist;

    public Mark(int idx, TaskList tasklist) {
        this.idx = idx;
        this.tasklist = tasklist;
    }

    @Override
    public String execute() {
        Task t = tasklist.get(idx - 1);
        t.mark();
        return "Nice! I've marked this task as done:\n" + t + "\n";
    }
}

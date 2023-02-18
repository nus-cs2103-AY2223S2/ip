package duke.commands;

import duke.backend.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.Todo;

public class MakeDeadline extends Command {
    private String description;
    private String by;
    private TaskList tasklist;

    public MakeDeadline(String description, String by, TaskList tasklist) {
        this.description = description;
        this.by = by;
        this.tasklist = tasklist;
    }

    @Override
    public String execute() {
        Task t = new Deadline(description, by);
        tasklist.add(t);
        return "Added this new Deadline: \n" + t;
    }
}

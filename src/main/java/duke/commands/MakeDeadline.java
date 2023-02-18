package duke.commands;

import duke.backend.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Task;

/**
 * Command to create a Deadline.
 */
public class MakeDeadline extends Command {
    private String description;
    private String by;
    private TaskList tasklist;

    /**
     * Constructor for a MakeDeadline command.
     * @param description The name of the Deadline.
     * @param by Due date of the Deadline.
     * @param tasklist The list to add the Deadline to.
     */
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

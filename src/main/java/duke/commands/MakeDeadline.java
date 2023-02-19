package duke.commands;

import duke.backend.Parser;
import duke.backend.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Task;

/**
 * Command to create a Deadline.
 */
public class MakeDeadline extends Make {
    private String description;
    private String by;
    private TaskList tasklist;

    /**
     * Constructor for a MakeDeadline command.
     * @param description The name of the Deadline.
     * @param by Due date of the Deadline.
     * @param tasklist The list to add the Deadline to.
     */
    public MakeDeadline(String description, String by, TaskList tasklist, Parser parser) {
        super(description, tasklist, parser);
        this.by = by;
    }

    @Override
    public String execute() {
        Task t = new Deadline(description, by);
        //  Guard Clause:
        Task duplicate = findDuplicates();
        if (duplicate != null) {
            return duplicateFound(t, duplicate);
        }
        tasklist.add(t);
        return "Added this new Deadline: \n" + t;
    }
}

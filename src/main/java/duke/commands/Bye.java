package duke.commands;

import duke.backend.TaskList;

/**
 * Command for exiting Duke.
 */
public class Bye extends Command {
    private TaskList tasklist;

    /**
     * Constructor for a Bye command.
     * @param tasklist The TaskList to save.
     */
    public Bye(TaskList tasklist) {
        this.tasklist = tasklist;
    }

    @Override
    public String execute() {
        tasklist.closeAndSave();
        return "Bye. Hope to see you soon!\n";
    }
}

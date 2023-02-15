package duke.commands;

import duke.Storage;
import duke.TaskCreationException;
import duke.TaskList;

/**
 * Command that represents adding of a deadline
 */
public class AddDeadlineCommand extends Command {
    private final String desc;
    private final String deadline;

    /**
     * Creates a new to-do command
     *
     * @param desc description of todo
     */
    public AddDeadlineCommand(String desc, String deadline) {
        super();
        this.desc = desc;
        this.deadline = deadline;
    }


    @Override
    public String execute(TaskList tl, Storage storage) throws TaskCreationException {
        tl.addDeadline(desc, deadline);
        storage.store(tl);
        return tl.toString();
    }
}

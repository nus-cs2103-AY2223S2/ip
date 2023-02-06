package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Command that finds tasks based on description
 */
public class FindCommand extends Command {
    private final String desc;

    /**
     * Creates a new find command
     *
     * @param desc description to find
     */
    public FindCommand(String desc) {
        super();
        this.desc = desc;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.find(desc).toString();
    }
}

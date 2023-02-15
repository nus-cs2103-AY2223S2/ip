package duke.commands;

import duke.Storage;
import duke.TaskCreationException;
import duke.TaskList;

/**
 * Command that represents adding of a task
 */
public class AddToDoCommand extends Command {
    private final String desc;

    /**
     * Creates a new to-do command
     *
     * @param desc description of to-do
     */
    public AddToDoCommand(String desc) {
        super();
        this.desc = desc;
    }


    @Override
    public String execute(TaskList tl, Storage storage) throws TaskCreationException {
        tl.addTodo(desc);
        storage.store(tl);
        return tl.toString();
    }
}

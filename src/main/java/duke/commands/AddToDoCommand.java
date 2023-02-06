package duke.commands;

import duke.Storage;
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
    public String execute(TaskList tl, Storage storage) {
        tl.addTodo(desc);
        storage.store(tl);
        return listAll(tl);
    }
}

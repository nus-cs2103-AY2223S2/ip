package duke.commands;

import duke.Storage;
import duke.TaskCreationException;
import duke.TaskList;

/**
 * Represents the adding of an event
 */
public class AddEventCommand extends Command {
    private final String desc;
    private final String from;

    private final String to;

    /**
     * Creates a new to-do command
     *
     * @param desc description of todo
     */
    public AddEventCommand(String desc, String from, String to) {
        super();
        this.desc = desc;
        this.from = from;
        this.to = to;

    }


    @Override
    public String execute(TaskList tl, Storage storage) throws TaskCreationException {
        tl.addEvent(desc, from, to);
        storage.store(tl);
        return tl.toString();
    }
}

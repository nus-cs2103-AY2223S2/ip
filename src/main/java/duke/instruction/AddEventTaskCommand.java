package duke.instruction;

import duke.task.EventTask;

/**
 * An instruction class that encapsulates the action of adding an event task into the given TaskList.
 */

public class AddEventTaskCommand extends AddTaskCommand {
    /**
     * Adds an event task into the given TaskList.
     *
     * @param task The event task to be added
     */
    public AddEventTaskCommand(EventTask task) {
        super(task);
    }
}

package duke.instruction;

import duke.task.EventTask;

/**
 * An instruction class that encapsulates the action of adding an event task into the given TaskList.
 */

public class AddEventTaskInstruction extends AddTaskInstruction{
    /**
     * Adds an event task into the given TaskList.
     *
     * @param task The event task to be added
     */
    public AddEventTaskInstruction(EventTask task) {
        super(task);
    }
}

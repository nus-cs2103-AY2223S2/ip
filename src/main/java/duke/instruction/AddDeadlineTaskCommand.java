package duke.instruction;

import duke.task.DeadlineTask;

/**
 * An instruction class that encapsulates the action of adding a deadline task into the given TaskList.
 */

public class AddDeadlineTaskCommand extends AddTaskCommand {
    /**
     * Adds a deadline task into the given TaskList.
     *
     * @param task the deadline task to be added
     */
    public AddDeadlineTaskCommand(DeadlineTask task) {
        super(task);
    }
}

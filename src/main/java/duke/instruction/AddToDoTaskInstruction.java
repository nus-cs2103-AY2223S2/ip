package duke.instruction;

import duke.task.TodoTask;

/**
 * An instruction class that encapsulates the action of adding a TD task into the given TaskList.
 */

public class AddToDoTaskInstruction extends AddTaskInstruction {
    /**
     * Adds a TD task into the given TaskList.
     *
     * @param task The TD task to be added
     */
    public AddToDoTaskInstruction(TodoTask task) {
        super(task);
    }
}

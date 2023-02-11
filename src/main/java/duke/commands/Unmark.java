package duke.commands;

import duke.TaskList;
import duke.commands.tasks.Task;

/**
 * This class handles the command to unmark tasks as completed (i.e. they are incomplete).
 */
public class Unmark extends Command {
    private static final String RESPONSE_HEADER = "OK, I've marked this task as not done yet:\n";
    private final int index;
    private Task unmarkedTask;

    /**
     * Creates the Unmark class.
     *
     * @param message The full command represented by this class.
     * @param index The index of the to-do list to be unmarked.
     */
    public Unmark(String message, int index) {
        super(message);
        this.index = index;
    }

    /**
     * Marks a task as incomplete.
     *
     * @param toDoList The task list to be edited.
     */
    @Override
    public void execute(TaskList toDoList) {
        toDoList.get(this.index).markUndone();
        this.unmarkedTask = toDoList.get(this.index);
    }

    @Override
    public String getResponseOutput() {
        return Unmark.RESPONSE_HEADER
                + "    "
                + this.unmarkedTask;
    }
}

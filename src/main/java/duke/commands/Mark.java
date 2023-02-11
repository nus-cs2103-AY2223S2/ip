package duke.commands;

import duke.TaskList;
import duke.commands.tasks.Task;

/**
 * This class handles the command to mark tasks as done.
 */
public class Mark extends Command {
    private static final String RESPONSE_HEADER = "Nice! I've marked this task as done:\n";
    private final int index;
    private Task markedTask;

    /**
     * Creates the Mark class.
     *
     * @param message The full command represented by this class.
     * @param index The index of the to-do list to be marked.
     */
    public Mark(String message, int index) {
        super(message);
        this.index = index;
    }

    /**
     * Marks a task at index to indicate that it has been completed.
     *
     * @param toDoList The task list to be edited.
     */
    @Override
    public void execute(TaskList toDoList) {
        toDoList.get(this.index).markDone();
        this.markedTask = toDoList.get(this.index);
    }

    @Override
    public String getResponseOutput() {
        return Mark.RESPONSE_HEADER
                + Command.INDENT
                + this.markedTask;
    }
}

package duke.commands;

import duke.TaskList;
import duke.commands.tasks.Task;

/**
 * This class handles addition commands.
 */
public class Add extends Command {
    private static final String RESPONSE_HEADER = "Got it! I've added this task:\n";
    private final Task task;

    /**
     * Creates the Add class.
     *
     * @param message The full command represented by this class.
     * @param task The task to be added to the task list.
     */
    public Add(String message, Task task) {
        super(message);
        this.task = task;
    }

    /**
     * Adds the task to the specified list.
     *
     * @param toDoList The task list to be edited.
     */
    @Override
    public void execute(TaskList toDoList) {
        toDoList.add(this.task);
    }

    @Override
    public String getResponseOutput() {
        return String.format(Add.RESPONSE_HEADER
                + Command.INDENT
                + "%s\n",
                this.task.toString());
    }
}

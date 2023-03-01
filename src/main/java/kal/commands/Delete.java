package kal.commands;

import kal.TaskList;
import kal.commands.tasks.Task;

/**
 * This class handles deletion commands.
 */
public class Delete extends Command {
    private static final String RESPONSE_HEADER = "Alrighty, this task is off your calendar:\n";
    private final int index;
    private Task removed;
    private int newSize;

    /**
     * Constructs a Delete object.
     *
     * @param message The full command message.
     * @param index The index in the task list to be deleted.
     */
    public Delete(String message, int index) {
        super(message);
        this.index = index;
        this.removed = null;
        this.newSize = 0;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param toDoList The task list to be edited.
     */
    @Override
    public void execute(TaskList toDoList) {
        this.removed = toDoList.get(this.index);
        this.newSize = toDoList.size() - 1;
        toDoList.remove(this.index);
    }

    @Override
    public String getResponseOutput() {
        return String.format(Delete.RESPONSE_HEADER
                + Command.INDENT
                + "%s\n"
                + "Now you have %d tasks in the list.",
                this.removed, this.newSize);
    }
}

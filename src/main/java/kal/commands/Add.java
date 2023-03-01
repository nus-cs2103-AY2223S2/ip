package kal.commands;

import java.util.List;

import kal.TaskList;
import kal.commands.tasks.Task;

/**
 * This class handles addition commands.
 */
public class Add extends Command {
    private static final String RESPONSE_HEADER = "Okay. This task is in your calendar meow:\n";
    private static final String DUPLICATE_HANDLER = "However, there this task has duplicate(s) "
            + "and is repeated at the following indexes:\n";
    private static final String DELETE_PROMPT = "I suggest deleting one of the duplicates! Or keep them there.."
            + " up to you.\n";
    private final Task task;
    private boolean hasDuplicate;
    private List<Integer> duplicateIndex;

    /**
     * Creates the Add class.
     *
     * @param message The full command represented by this class.
     * @param task The task to be added to the task list.
     */
    public Add(String message, Task task) {
        super(message);
        this.task = task;
        this.hasDuplicate = false;
    }

    /**
     * Adds the task to the specified list.
     *
     * @param toDoList The task list to be edited.
     */
    @Override
    public void execute(TaskList toDoList) {
        this.hasDuplicate = toDoList.contains(this.task);
        toDoList.add(this.task);
        this.duplicateIndex = toDoList.getOccurrences(this.task);
    }

    @Override
    public String getResponseOutput() {
        String result = String.format(Add.RESPONSE_HEADER
                + Command.INDENT
                + "%s\n",
                this.task.toString());
        if (this.hasDuplicate) {
            result += Add.DUPLICATE_HANDLER;
            result += this.duplicateIndex.toString() + "\n";
            result += Add.DELETE_PROMPT;
        }
        return result;
    }

    @Override
    public String toString() {
        return this.getResponseOutput();
    }
}

package duke.Commands;

import duke.TaskList;

/**
 * This class handles the command to mark tasks as done
 */
public class Mark extends Command {
    private final int index;

    public Mark(String message, int index) {
        super(message);
        this.index = index;
    }

    /**
     * Marks a task at index to indicate that it has been completed
     *
     * @param toDoList The task list to be edited
     */
    @Override
    public void execute(TaskList toDoList) {
        toDoList.get(this.index).markDone();
        System.out.println("Nice! I've marked this task as done:\n" + "    " +
                toDoList.get(this.index));
    }
}

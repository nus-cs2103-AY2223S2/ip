package duke.commands;

import duke.TaskList;

/**
 * This class handles the command to unmark tasks as completed (i.e. they are incomplete)
 */
public class Unmark extends Command {
    private final int index;

    public Unmark(String message, int index) {
        super(message);
        this.index = index;
    }

    /**
     * Marks a task as incomplete
     *
     * @param toDoList The task list to be edited
     */
    @Override
    public void execute(TaskList toDoList) {
        toDoList.get(this.index).markUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" + "    " +
                toDoList.get(this.index));
    }
}

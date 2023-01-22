package duke.command;

import duke.DukeException;
import duke.task.TaskList;

public class MarkCommand extends Command {

    private final TaskList taskList;
    private final int taskIndex;
    private final boolean isDone;

    /**
     * Constructor for MarkCommand.
     * Marks or unmarks the task as completed.
     * @param taskList TaskList of Duke's tasks.
     * @param taskIndex Index of the task in the task list.
     * @param isDone Completion status of the task.
     */
    public MarkCommand(TaskList taskList, int taskIndex, boolean isDone) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
        this.isDone = isDone;
    }

    @Override
    public boolean execute() throws DukeException {
        taskList.get(taskIndex).setIsDone(isDone);

        if (isDone) {
            System.out.println("This task is now done, what's next?");
        }
        else {
            System.out.println("This task is now not done, how disappointing...");
        }
        System.out.println(taskList.get(taskIndex));

        return false;
    }
}

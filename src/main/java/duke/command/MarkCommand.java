package duke.command;

import duke.DukeException;
import duke.task.TaskList;

/**
 * A command that sets the task as completed or not.
 * @author Junyi
 */
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
    public String execute() throws DukeException {
        StringBuilder stringBuilder = new StringBuilder();
        taskList.get(taskIndex).setIsDone(isDone);

        if (isDone) {
            stringBuilder.append("This task is now done, what's next?\n");
        } else {
            stringBuilder.append("This task is now not done, get to work!\n");
        }
        stringBuilder.append(taskList.get(taskIndex));

        return stringBuilder.toString();
    }
}

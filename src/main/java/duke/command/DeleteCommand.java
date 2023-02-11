package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * DeleteCommand to represent command to delete task at a specific index in the task list.
 */
public class DeleteCommand extends AddCommand {
    public static final String COMMAND_WORD = "delete";
    private int taskIndex;

    /**
     * Constructor for DeleteCommand
     * @param taskIndex
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String response;
        try {
            Task taskRemoved = tasks.getTaskAtIndex(taskIndex);
            tasks.deleteTask(taskIndex);
            response = "Noted. I've removed this task: \n" + taskRemoved
                    + String.format("\nNow you have %d tasks in the list.", tasks.getSize());
        } catch (DukeException e) {
            response = e.getMessage();
        }
        Ui.showResponse(response);
        this.responseFromDukeAfterExecution = response;
    }
}

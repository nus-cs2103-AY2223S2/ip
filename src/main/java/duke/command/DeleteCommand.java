package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.TaskList;
import duke.Storage;
import duke.task.Task;

/**
 * DeleteCommand that has task index.
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
        String response = "DEFAULT MESSAGE";
        try {
            Task taskRemoved = tasks.getTaskAtIndex(taskIndex);
            tasks.deleteTask(taskIndex);
            response = "Noted. I've removed this task: \n" + taskRemoved +
                    String.format("\nNow you have %d tasks in the list.", tasks.getSize());
        } catch (DukeException e) {
            response = e.getMessage();
        }
        Ui.showResponse(response);
        this.responseFromDukeAfterExecution = response;
    }
}
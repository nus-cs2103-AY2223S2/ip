package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * UnmarkCommand.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int taskIndex;
    /**
     * Constructor for UnmarkCommand.
     * @param taskIndex
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String response;
        try {
            tasks.unmarkTask(taskIndex);
            response = "OK, I've marked this task as not done yet:\n" + tasks.getTaskAtIndex(taskIndex).toString();
        } catch (DukeException e) {
            response = e.getMessage();
        }
        Ui.showResponse(response);
        this.responseFromDukeAfterExecution = response;
    }
}

package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * MarkCommand that represents command to mark a certain task as done.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int taskIndex;

    /**
     * Constructor for MarkCommand.
     * @param taskIndex
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String response = "DEFAULT MESSAGE";
        try {
            tasks.markTask(taskIndex);
            response = "Nice! I've marked this task as done:\n" + tasks.getTaskAtIndex(taskIndex).toString();
        } catch (DukeException e) {
            response = e.getMessage();
        }
        Ui.showResponse(response);
        this.responseFromDukeAfterExecution = response;
    }
}

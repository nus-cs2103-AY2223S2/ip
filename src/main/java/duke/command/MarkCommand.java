package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a MarkCommand that implements the Command interface.
 */
public class MarkCommand implements Command {
    private int taskNumber;
    private boolean toMark;

    /**
     * A constructor for MarkCommand.
     * @param taskNumber The task number to mark.
     * @param toMark Indicates whether to mark or unmark.
     */
    public MarkCommand(int taskNumber, boolean toMark) {
        this.taskNumber = taskNumber;
        this.toMark = toMark;
    }
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        String toDisplay;
        if (toMark) {
            tasks.markTask(taskNumber - 1);
            toDisplay = "Ok, I've marked this task as done:\n" + tasks.getTask(taskNumber - 1);
        } else {
            tasks.unmarkTask(taskNumber - 1);
            toDisplay = "Ok, I've marked this task as undone:\n" + tasks.getTask(taskNumber - 1);
        }
        ui.displayMessage(toDisplay);
        return toDisplay;
    }
}

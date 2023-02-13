package jarvis.command;

import jarvis.exception.command.CommandParseException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;


/**
 * Command class to mark tasks as done or undone.
 */
public class MarkCommand extends Command {
    private final int index;
    private final boolean isDone;

    /**
     * Constructor for a command to mark tasks as done or undone.
     * @param body String 1-based index of the target task.
     * @param isDone Whether the task is to be marked as done or undone.
     */
    public MarkCommand(String body, boolean isDone) {
        super(isDone ? Action.MARK_DONE : Action.MARK_UNDONE, body);

        int index;
        try {
            index = Integer.parseInt(body);
        } catch (NumberFormatException ignored) {
            index = -1;
        }
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            ui.print(taskList.setTaskDone(this.index, this.isDone));
        } catch (CommandParseException e) {
            ui.printError(e.getFriendlyMessage());
        }
    }
}

package jarvis.command;

import jarvis.Storage;
import jarvis.task.TaskList;
import jarvis.Ui;
import jarvis.exception.InvalidParameterException;


/**
 * Command class to mark tasks as done or undone.
 */
public class MarkCommand extends Command {
    private final int index;
    private final boolean isDone;

    public MarkCommand(Action action, String body) {
        super(action, body, null);

        int index = -1;
        try {
            index = Integer.parseInt(body);
        } catch (NumberFormatException ignored) {}
        this.index = index;
        this.isDone = action == Action.MARK_DONE;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            ui.print(taskList.setTaskDone(this.index, this.isDone));
        } catch (InvalidParameterException e) {
            ui.printError(e.getFriendlyMessage());
        }
    }
}

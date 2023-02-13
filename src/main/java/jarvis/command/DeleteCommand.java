package jarvis.command;

import jarvis.exception.command.CommandParseException;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;


/**
 * Command class to delete tasks.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructor for a command that deletes a task.
     * @param body String 1-based index of the task to delete.
     */
    public DeleteCommand(String body) {
        super(Action.DELETE_TASK, body);

        int index;
        try {
            index = Integer.parseInt(body);
        } catch (NumberFormatException ignored) {
            index = -1;
        }
        this.index = index;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            ui.print(taskList.deleteTask(this.index));
        } catch (CommandParseException e) {
            ui.printError(e.getFriendlyMessage());
        }
    }
}

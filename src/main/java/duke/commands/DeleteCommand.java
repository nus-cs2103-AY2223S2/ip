package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates delete command and its arguments.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";
    private String[] tokens;

    /**
     * Constructs a new Delete Command.
     *
     * @param tokens Arguments of the command.
     */
    public DeleteCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.deleteTask(
                Character.getNumericValue(tokens[1].charAt(0)), storage);
        ui.showDeleteDone(deletedTask, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

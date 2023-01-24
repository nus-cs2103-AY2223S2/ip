package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.StorageFileException;
import duke.storage.CommandHistory;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The UndoCommand class that undo the previous command on the tasklist
 */
public class UndoCommand extends Command {
    /**
     * Execute the undo command on the tasklist
     *
     * @param tasks tasklist
     * @param ui user interface
     * @param storage storage
     * @param commandHistory command history
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, CommandHistory commandHistory)
            throws InvalidInputException, StorageFileException {
        if (commandHistory.isEmpty()) {
            throw new InvalidInputException("No previous commands to undo");
        }
        TaskList previousState = commandHistory.pop();
        tasks.setTasks(previousState.getTasks());
        storage.saveTaskList(tasks);
        ui.appendResponse("Undo Successful!");
    }
}

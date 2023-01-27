package duke.command;

import duke.exception.InvalidInputException;
import duke.exception.StorageFileException;
import duke.parser.ErrorMessage;
import duke.storage.CommandHistory;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The UndoCommand class that undo the previous command on the tasklist
 */
public class UndoCommand extends Command {
    private static final String UNDO_SUCCESS_MESSAGE = "Undo Successful!";
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
        // Check if command history is empty
        if (commandHistory.isEmpty()) {
            throw new InvalidInputException(ErrorMessage.NO_COMMAND_ERROR_MESSAGE);
        }

        // Get the previous state of the task list
        TaskList previousState = commandHistory.pop();
        // Set the task list to the previous state
        tasks.setTasks(previousState.getTasks());
        // Save the task list to storage
        storage.saveTaskList(tasks);
        // Display success message
        ui.appendResponse(UNDO_SUCCESS_MESSAGE);
    }
}

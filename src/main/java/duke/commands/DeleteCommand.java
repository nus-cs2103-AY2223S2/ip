package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    protected int taskNumber;

    /**
     * A constructor to initialize a delete command.
     *
     * @param taskNumber The task number of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, TextUi ui) {
        try {
            Task t = taskList.getTask(taskNumber - 1);
            taskList.deleteTask(taskNumber);
            storage.save(taskList);
            return ui.printDeletedTask(t, taskList);
        } catch (DukeException e) {
            return ui.printError(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            return ui.printError("Huh... the task does not exist.");
        }
    }
}

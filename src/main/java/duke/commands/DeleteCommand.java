package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

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
    public void execute(TaskList taskList, Storage storage) {
        try {
            taskList.deleteTask(taskNumber);
            storage.save(taskList);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}

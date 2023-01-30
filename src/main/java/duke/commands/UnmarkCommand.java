package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an unmark command.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    protected int taskNumber;

    /**
     * A constructor to initialize an unmark command.
     *
     * @param taskNumber The task number of the task to be unmarked.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            taskList.unmarkTask(taskNumber);
            storage.save(taskList);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

}

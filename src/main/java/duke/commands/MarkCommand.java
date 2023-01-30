package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a mark command.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    protected int taskNumber;

    /**
     * A constructor to initialize a mark command.
     *
     * @param taskNumber The task number of the task to be marked.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            taskList.markTask(taskNumber);
            storage.save(taskList);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

}

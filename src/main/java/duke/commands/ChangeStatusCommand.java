package duke.commands;

import java.util.Objects;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Represents the command for changing the status of an existing task.
 */
public class ChangeStatusCommand extends Command {

    private String changeType;
    private int taskNumber;

    /**
     * Creates a new ChangeStatusCommand.
     * @param changeType mark or unmark.
     * @param taskNumber index of task in task list.
     */
    public ChangeStatusCommand(String changeType, int taskNumber) {
        this.changeType = changeType;
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(Tasks tasks, Ui ui, Storage storage) {
        if (!Objects.equals(this.changeType, "mark")) {
            return tasks.markTaskUndone(taskNumber);
        } else {
            return tasks.markTaskDone(taskNumber, false);
        }
    }
}

package duke.commands;

import java.util.Objects;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

public class ChangeStatusCommand extends Command {

    private String changeType;
    private int taskNumber;

    public ChangeStatusCommand(String changeType, int taskNumber) {
        this.changeType = changeType;
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        if (!Objects.equals(this.changeType, "mark")) {
            tasks.markTaskUndone(taskNumber);
        } else {
            tasks.markTaskDone(taskNumber, false);
        }
    }
}

package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private int taskNumber;
    private boolean isMarkingTask;

    public MarkCommand(int taskNumber, boolean isMarkingTask) {
        super();
        this.taskNumber = taskNumber;
        this.isMarkingTask = isMarkingTask;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (isMarkingTask) {
            taskList.markTask(this.taskNumber);
        } else {
            taskList.unmarkTask(this.taskNumber);
        }
    }
}

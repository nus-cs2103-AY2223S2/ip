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

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MarkCommand)) {
            return false;
        }

        MarkCommand c = (MarkCommand) o;
        if (this.taskNumber == c.taskNumber && this.isMarkingTask == c.isMarkingTask) {
            return true;
        } else {
            return false;
        }
    }
}

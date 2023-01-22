package duke.command;

import duke.Ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class MarkCommand extends Command{
    protected String fullCommand;
    protected String markOrNot;
    protected int taskNumber;
    public MarkCommand(String fullCommand, String markOrNot, int taskNumber) {
        this.fullCommand = fullCommand;
        this.markOrNot = markOrNot;
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.markOrNot.equals("unmark")){
            tasks.unmark(taskNumber);
        } else if (this.markOrNot.equals("mark")) {
            tasks.mark(taskNumber);

        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}

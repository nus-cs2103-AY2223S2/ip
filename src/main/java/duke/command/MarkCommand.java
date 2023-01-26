package duke.command;

import duke.exception.DukeBadInstructionFormatException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {

    public MarkCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeBadInstructionFormatException {
        int index = Command.getMarkIndex(this.fullCommand) - 1;
        storage.fileMarkTask(index);
        tasks.markTask(index);
        String taskDescription = tasks.taskToString(index);
        ui.showMarkedTask(taskDescription);
    }

    @Override
    public boolean isExit() {

        return false;
    }
}


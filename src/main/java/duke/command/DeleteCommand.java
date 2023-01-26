package duke.command;

import duke.exception.DukeBadInstructionFormatException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String fullCommand) {

        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeBadInstructionFormatException {
        int index = Command.getMarkIndex(this.fullCommand) - 1;

        if (index >= tasks.getLength()) {
            throw new DukeBadInstructionFormatException("Delete index" +
                    "out of range.");
        }

        storage.fileDeleteTask(index);
        Task removed = tasks.delete(index);
        ui.showDeletedTask(removed, tasks);

    }

    @Override
    public boolean isExit() {

    return false;
    }
}

package duke.command;

import duke.exception.DukeBadInstructionFormatException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ClearCommand extends Command {

    public ClearCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeBadInstructionFormatException {
        String[] splitted = this.fullCommand.split(" ");
        if (splitted.length != 1) {
            throw new DukeBadInstructionFormatException(
                    "Usage of *clear* command: clear");
        }
        tasks.clearAllTasks();
        storage.clearAllTasks();
        ui.showClearTasksMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

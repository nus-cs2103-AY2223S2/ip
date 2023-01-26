package duke.command;

import duke.exception.DukeBadInstructionFormatException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String fullCommand) {

        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeBadInstructionFormatException {
        String[] splitted = this.fullCommand.split(" ");
        if (splitted.length != 1) {
            throw new DukeBadInstructionFormatException("To exit, type *bye*");
        }
        ui.showByeMessage();
    }

    @Override
    public boolean isExit() {

        return true;
    }
}

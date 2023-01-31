package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
        super(CommandType.BYE);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showBye();
    }
}

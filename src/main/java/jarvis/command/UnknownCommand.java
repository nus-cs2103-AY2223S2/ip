package jarvis.command;

import jarvis.Storage;
import jarvis.TaskList;
import jarvis.Ui;


public class UnknownCommand extends Command {

    public UnknownCommand() {
        super(null, null, null);
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.printStandard(Ui.Response.CONFUSED);
    }
}

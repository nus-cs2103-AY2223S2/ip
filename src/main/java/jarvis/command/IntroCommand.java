package jarvis.command;

import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;

/**
 * Command class for a Jarvis intro.
 */
public class IntroCommand extends Command {
    public IntroCommand() {
        super(Action.INTRO, null);
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.printLogo();
        ui.printStandard(Ui.Response.INTRO);
    }
}

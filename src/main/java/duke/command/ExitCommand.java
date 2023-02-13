package duke.command;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;

public class ExitCommand extends Command {
    @Override
    public String execute(Tasklist tasklist) throws Exception {
        Storage.save(tasklist);
        return Ui.showGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

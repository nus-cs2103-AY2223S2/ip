package duke.command;

import duke.Tasklist;
import duke.Ui;

/**
 * Class representing the exit command
 */
public class ExitCommand extends Command {

    /**
     * @inheritDoc
     */
    @Override
    public String execute(Tasklist tasklist, int saveNo) throws Exception {
        return Ui.showGoodbyeMessage();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

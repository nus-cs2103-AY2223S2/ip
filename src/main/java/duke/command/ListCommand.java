package duke.command;

import duke.Tasklist;
import duke.Ui;

/**
 * Class representing the list command.
 */
public class ListCommand extends Command {

    /**
     * @inheritDoc
     */
    @Override
    public String execute(Tasklist tasklist, int saveNo) throws Exception {
        return Ui.showList(tasklist);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

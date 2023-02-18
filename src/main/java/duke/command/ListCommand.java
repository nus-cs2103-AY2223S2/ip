package duke.command;

import duke.Tasklist;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(Tasklist tasklist, int saveNo) throws Exception {
        return Ui.showList(tasklist);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}

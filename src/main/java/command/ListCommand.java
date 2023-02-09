package command;

import duke.DukeList;
import duke.Ui;

public class ListCommand extends Command{
    private DukeList dukeList;
    private Ui ui;

    public ListCommand(DukeList dukeList, Ui ui) {
        this.dukeList = dukeList;
        this.ui = ui;
    }

    @Override
    public void execute() {
        if (this.dukeList.isEmpty()) {
            this.ui.addStatement("Yo there's nothing in the list.");
        } else {
            this.ui.addStatement(this.dukeList.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

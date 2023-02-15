package command;

import duncan.DuncanList;
import duncan.Ui;

public class ListCommand extends Command{
    private DuncanList duncanList;
    private Ui ui;

    public ListCommand(DuncanList duncanList, Ui ui) {
        this.duncanList = duncanList;
        this.ui = ui;
    }

    @Override
    public void execute() {
        if (this.duncanList.isEmpty()) {
            this.ui.addStatement("Yo there's nothing in the list.");
        } else {
            this.ui.addStatement(this.duncanList.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

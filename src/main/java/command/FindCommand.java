package command;

import duke.Storage;
import duke.Ui;
import task.Tasklist;

public class FindCommand implements Command{
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Ui ui, Tasklist list, Storage storage) {
        list.find(this.keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

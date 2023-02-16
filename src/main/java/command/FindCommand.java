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
    public String execute(Ui ui, Tasklist list, Storage storage) {
        Tasklist matchedList = list.find(this.keyword);
        return ui.getMatchedListReply(matchedList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

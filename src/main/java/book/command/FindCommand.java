package book.command;

import book.Storage;
import book.TaskList;
import book.Ui;

public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(Storage storage, TaskList list, Ui ui) {
        ui.showMatchingTasksList(list, this.keyword);
    }
}

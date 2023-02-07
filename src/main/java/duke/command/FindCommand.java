package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute(TaskList l, Ui ui, Storage s) {
        return l.find(keyword, ui);
    }
}

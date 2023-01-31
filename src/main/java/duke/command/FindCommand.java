package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList l, Ui ui, Storage s) {
        l.find(keyword, ui);
    }
}

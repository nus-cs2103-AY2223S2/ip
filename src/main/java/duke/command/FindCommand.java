package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute(TaskList l, Ui ui, Storage s, Command prevCommand, Duke duke) {
        String errorMsg = saveToFile(s, l, ui, prevCommand);
        String findMsg = l.find(keyword, ui);
        return errorMsg + "\n" + findMsg;
    }
}

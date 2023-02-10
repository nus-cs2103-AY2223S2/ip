package duke.command;

import duke.Duke;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public String execute(TaskList l, Ui ui, Storage s, Command prevCommand, Duke duke) {
        String errorMsg = saveToFile(s, l, ui, prevCommand);
        String fullMsg = errorMsg + "\n" + ui.showList(l);
        return fullMsg;
    }
}

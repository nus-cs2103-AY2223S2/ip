package luffy.command;

import luffy.storage.TaskList;
import luffy.ui.Ui;

/**
 * The ListCommand class encapsulates the variables and methods related to List commands.
 */
public class ListCommand extends Command {
    private static final String LIST_COMMAND = "list";

    public ListCommand() {
        super(LIST_COMMAND);
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        return ui.showList(taskList);
    }
}

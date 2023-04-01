package peppa.commands;

import peppa.Storage;
import peppa.TaskList;
import peppa.Ui;

/**
 * Represents a list command that displays the current tasklist.
 */
public class ListCommand implements Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Constructs a list command.
     */
    public ListCommand() { }

    @Override
    public String execute(TaskList taskList, Ui screen, Storage storage) {
        return Ui.getTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, TextUi ui) {
        storage.save(taskList);
        return ui.printTaskList(taskList);
    }
}

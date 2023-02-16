package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, TextUi ui) {
        storage.save(taskList);
        System.exit(0);
        return ui.printGoodbye();
    }
}

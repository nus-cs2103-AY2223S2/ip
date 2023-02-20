package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TextUi;

/**
 * Finds task(s) that matches the given input keyword upon execution
 */

public class FindCommand extends Command {
    private final String DETAILS;

    public FindCommand(String details) {
        this.DETAILS = details;
    }

    @Override
    public String execute(TaskList tasksList, TextUi ui, Storage storage) {
        return ui.showFindMessage(tasksList, this.DETAILS);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

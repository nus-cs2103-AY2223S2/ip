package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command that finds tasks containing a given keword.
 */
public class FindCommand extends Command {

    private final String keyword;
    FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command.
     * @param tasks List of tasks.
     * @param ui UI handler.
     * @param storage Storage to save and load list of tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBunny();
        return ui.find(keyword, tasks);
    }

}

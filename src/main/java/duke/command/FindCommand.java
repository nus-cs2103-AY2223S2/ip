package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Finds task when user input indicates find.
 */
public class FindCommand extends Command {
    private final String KEYWORD;

    public FindCommand(String keyword) {
        this.KEYWORD = keyword;
    }

    /**
     * Finds task from the list of tasks using the keyword provided.
     *
     * @param tasks List of tasks.
     * @param ui Ui object that handles all Ui actions.
     * @param storage Storage object that handles all Storage actions.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = tasks.findTask(this.KEYWORD);
        return ui.getFindOutput(foundTasks);
    }
}

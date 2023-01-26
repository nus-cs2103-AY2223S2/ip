package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Finds task when user input indicates find.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds task from the list of tasks using the keyword provided.
     *
     * @param tasks List of tasks.
     * @param ui Ui object that handles all Ui actions.
     * @param storage Storage object that handles all Storage actions.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = tasks.findTask(this.keyword);
        ui.showFind(foundTasks);
    }
}

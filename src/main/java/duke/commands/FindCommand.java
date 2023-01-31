package duke.commands;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;


public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Calls TaskList find method to get the list with matching tasks.
     * Prompts Ui to display list as an indexed list.
     * @param tasks Existing TaskList used by the main Duke class.
     * @param ui Existing Ui used by the main Duke class.
     * @param storage Existing Storage used by the main Duke class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchingTasks = tasks.find(this.keyword);
        if (matchingTasks.size() > 0) {
            ui.showToUser("Here are the matching tasks in your list: ");
            ui.showIndexedList(matchingTasks);
        } else {
            ui.showToUser("No matching tasks were found.");
        }
    }


}
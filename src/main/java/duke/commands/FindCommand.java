package duke.commands;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.utils.Formatter;

/**
 * Represents FindCommand that will display tasks matching a specific keyword
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand
     * @param keyword used to match with tasks in task list
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Calls TaskList find method to get the list with matching tasks.
     * Prompts Ui to display list as an indexed list.
     * @param tasks Existing TaskList used by the main Duke class.
     * @param storage Existing Storage used by the main Duke class.
     * @return output to be shown to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        ArrayList<Task> matchingTasks = tasks.find(this.keyword);
        if (matchingTasks.size() > 0) {
            return Formatter.formatMultipleMessages("SEARCHING... Here are the matching tasks: ",
                    Formatter.formatIndexedList(matchingTasks));
        } else {
            return "SEARCHING... ERROR! No matching tasks were found. \n";
        }
    }
}

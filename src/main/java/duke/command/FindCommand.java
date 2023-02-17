package duke.command;

import java.util.List;

import duke.Task;
import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Handles the appropriate tasks when performing a FindCommand by Duke.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructor for FindCommand
     * @param keyword keyword to be filtered by
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    /**
     * Overridden method to handle the specific tasks to be carried out when performing deletion.
     * @param tasks a list of tasks.
     * @param ui Ui class to handle display messages.
     * @param storage Storage to handle saving/loading of data to/from the list of task.
     * @return Duke's response message.
     */
    @Override
    public String initCommand(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.find(keyword);
        if (matchingTasks.isEmpty()) {
            return "No matching tasks, please try again!";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the matching tasks in your list:\n");
            int count = 1;
            for (Task t : matchingTasks) {
                sb.append(count++ + "." + t.toString() + "\n");
            }
            return sb.toString();
        }
    }
}

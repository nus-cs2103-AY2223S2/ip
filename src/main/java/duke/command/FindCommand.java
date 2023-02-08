package duke.command;

import duke.Task;
import duke.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.List;

public class FindCommand extends Command {
    /**
     * Handles the appropriate tasks when performing an DeleteCommand by Duke.
     */

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
                sb.append(count++  + "." + t.toString() + "\n");
            }
            return sb.toString();
        }
    }
}

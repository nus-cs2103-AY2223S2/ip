package kuromi.command;

import kuromi.storage.Storage;
import kuromi.task.TaskList;
import kuromi.view.Ui;

/**
 * Command to find the tasks that contains the keyword from the user's input.
 */
public class FindCommand extends Command {
    /** The keyword to be searched **/
    private String keyword;

    /**
     * Main constructor
     * @param keyword The keyword to be searched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the tasks that contain the keyword input from the user.
     * Asks UI to show the output.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filtered = tasks.find(this.keyword);
        return ui.show(getReply(filtered));
    }

    private String getReply(TaskList filtered) {
        String msg = "Here are the matching tasks in your list:\n";
        msg += filtered.toString();
        return msg;
    }
}

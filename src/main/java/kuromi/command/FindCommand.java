package kuromi.command;

import kuromi.Storage;
import kuromi.Ui;
import kuromi.task.TaskList;

/**
 * Command to find the tasks that contains the keyword from the user's input.
 */
public class FindCommand extends Command {
    /** The keyword to be searched **/
    private String keyword;

    /**
     * kuromi.MainWindow.kuromi.KuromiException.Main constructor
     * @param keyword The keyword to be searched.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Find the tasks that contain the keyword input from the user.
     * Ask UI to show the output.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filtered = tasks.find(this.keyword);
        String msg = "Here are the matching tasks in your list:\n";
        msg += filtered.toString();
        return ui.show(msg);
    }
}

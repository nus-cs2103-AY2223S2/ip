package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the command to find tasks based on a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches through tasks to find tasks that contain given keyword.
     * Outputs matching tasks to user via the ui.
     *
     * @param tasks TaskList that contains all the current tasks.
     * @param ui Ui that communicates with the user.
     * @param storage Storage that backups the saving of tasks.
     * @return string reply to be shown to user after executing this command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            boolean hasKeyword = task.toString().contains(this.keyword);
            if (hasKeyword) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.size() > 0) {
            String msg = "I sense something... \n";
            msg += "Eureka! I've found the following matching task(s): \n";
            msg += matchingTasks.print();
            msg += "\n";
            return msg;
        } else {
            return "Oh no, I couldn't find any matching tasks! :( \n";
        }
    }
}

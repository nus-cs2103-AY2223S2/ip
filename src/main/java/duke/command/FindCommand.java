package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to find a task by searching for a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor for class FindCommand.
     * @param keyword the keyword to find.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find all matching Tasks.
     * @param tasks TaskList containing all the currently stored Tasks.
     * @param ui Ui that deals with interactions with the user.
     * @param storage Storage that loads and saves tasks to the file containing currently stored Tasks.
     * @return the response from Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskToString = tasks.get(i).toString();
            if (taskToString.contains(this.keyword)) {
                matchingTasks.add(task);
            }
        }
        String dukeResponse = "";
        if (matchingTasks.size() == 0) {
            dukeResponse = "There are no matching tasks!";
        } else {
            dukeResponse += "Here are the matching tasks in your list:\n";
            dukeResponse += new ListCommand().execute(matchingTasks, ui, storage);
        }
        return dukeResponse;
    }

    /**
     * Checks if the Command terminates the Duke Program.
     * @return false as FindCommand does not terminate the Duke program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

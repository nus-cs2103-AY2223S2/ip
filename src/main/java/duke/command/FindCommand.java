package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Finds the task with a certain keyword.
 */
public class FindCommand extends Command {
    private String userInput;

    /**
     * Main constructor
     *
     * @param userInput keyword of the task.
     */
    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Finds the task with a certain keyword.
     * Asks UI to list the tasks that contain the keyword.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList filteredTasks = tasks.find(userInput);
        return Ui.showFind(filteredTasks.getAllTasks());
    }
}
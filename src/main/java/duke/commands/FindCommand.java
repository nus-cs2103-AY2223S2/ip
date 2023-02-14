package duke.commands;

import java.util.List;
import java.util.stream.Collectors;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The FindCommand class implements the action of finding tasks by searching for a keyword.
 *
 * @author Chia Jeremy
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Class constructor for find command.
     *
     * @param keyword the word to search
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     * Lists out all tasks with the specified keyword.
     *
     * @param storage the file to save the tasks
     * @param tasks   the task lists
     * @param ui      the interface that deals with the interactions with users
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        List<Task> filteredTasks = filter(tasks);
        ui.setResponse("Here are the matching tasks in your list (on the right >>>)");
        ui.setTasksToDisplay(filteredTasks);
    }

    private List<Task> filter(TaskList tasks) {
        return tasks.get()
                .stream()
                .filter(task -> task.toString().contains(this.keyword))
                .collect(Collectors.toList());
    }
}

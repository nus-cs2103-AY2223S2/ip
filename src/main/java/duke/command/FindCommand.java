package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.dukeexception.CommandException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * FindCommand class extends Command class.
 */
public class FindCommand extends Command {

    /**
     * Constructor.
     * @param request the request of the user.
     */
    public FindCommand(String request) {
        super(request);
    }

    /**
     * Displays the tasks that matches the keyword
     * @param tasks the task list
     * @param ui the ui instance
     * @param storage the storage instance
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String description = this.unwrap()[0];

            System.out.println("Here are the tasks in your list:");
            List<Task> immutableTaskList = tasks.getList();

            ArrayList<Task> resultList = new ArrayList<Task>();
            immutableTaskList.forEach(task -> {
                if (task.getDescription().contains(description)) {
                    resultList.add(task);
                }
            });

            ui.showTaskList(resultList);
        } catch (CommandException error) {
            ui.showError(error);
        }
    }
}

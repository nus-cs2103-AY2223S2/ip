package command;

import java.util.ArrayList;
import java.util.List;

import dukeexception.CommandException;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

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
     * @param taks the task list
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

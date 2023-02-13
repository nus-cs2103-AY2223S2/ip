package henz.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import henz.henzexception.CommandException;
import henz.storage.Storage;
import henz.tasklist.TaskList;
import henz.tasks.Task;
import henz.ui.Ui;

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
     * @param tasks   the task list
     * @param ui      the ui instance
     * @param storage the storage instance
     * @return string
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        try {
            String description = this.unwrap()[0];

            System.out.println("Here are the tasks in your list:");
            List<Task> immutableTaskList = tasks.getList();

            Stream<Task> filteredList = immutableTaskList
                    .stream()
                    .filter(task -> task.getDescription().contains(description));

            ArrayList<Task> resultList = new ArrayList<Task>(
                    filteredList
                            .collect(Collectors.toList())
                    );

            String result = "Here are the tasks in your list:";
            int index = 1;
            for (Task task : resultList) {
                result += ("\n" + index + ". " + task.toString());
                index++;
            }

            return result;

        } catch (CommandException error) {
            return error.getMessage();
        }
    }
}
